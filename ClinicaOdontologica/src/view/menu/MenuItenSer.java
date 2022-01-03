package view.menu;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import dao.ItensServicoConsultaDAO;
import bancoDeDados.BancoDadosException;
import beans.ItensServicoConsulta;



public class MenuItenSer {
	String aux;
	public void String() throws BancoDadosException {
		int response = 0;

		do {

			String[] menupri = { "CADASTRAR", "EDITAR", "EXCLUIR", "CONSULTAR",
					"SAIR" };
			response = JOptionPane
					.showOptionDialog(
							null // Center in window.
							,
							"SELECIONE A OP��O DESEJADA!"
									+ "\n\nCADASTRAR\nNo Bot�o de \"CADASTRAR\" � possivel fazer o cadastramento de servi�o na consulta.\n"
									+ "Cada iten que abrir tem que ser preenchido de forma correta, pois n�o ir� cadastrar.\n"
									+ "Caso seja deixado algum campo em branco, o cadastramento ser� cancelado!"
									+ "\n\nEDITAR\nNo Bot�o de \"EDITAR\" � possivel fazer a altera��o das informa��es do servi�o na consulta.\n"
									+ "Cada iten que abrir tem que ser preenchido de forma correta, pois n�o ir� realizar \nas altera��es.\n"
									+ "Caso seja deixado algum campo em branco, a altera��es ir� continuar com as \ninforma��es anteriores"
									+ "\n\nEXCLUIR\nNo Bot�o de \"EXCLUIR\" � possivel excluir um servi�o na consulta. Para excluir � necess�rio\nter "
									+ "o c�digo do servi�o, para consegui-lo clique no bot�o \"CONSULTAR\"!"
									+ "\n\nCONSULTAR\nNo Bot�o de \"CONSULTAR\" � possivel consultar todos os servi�os na consulta cadastrados.\n\n\n",
							"****************************** ITENS DE SERVI�OS ******************************" // Title
							, JOptionPane.YES_NO_OPTION // Option type
							, JOptionPane.PLAIN_MESSAGE // messageType
							, null // Icon (none)
							, menupri // Button text as above.
							, "None of your business" // Default button's label
					);
			switch (response) {
			case 0:
				cadISC();
				break;
			case 1:
				alterarISC();
				break;
			case 2:
				excluirISC();
				break;
			case 3:
				consultaISC();
				break;

			default:
				break;
			}
		} while (response != -1 && response != 4);
	}

	// ************************************************************
	// *****************CADASTRAR ISC*************************
	// ************************************************************
	public void cadISC() throws BancoDadosException {
		ItensServicoConsulta isc = new ItensServicoConsulta();
		while (true) {
			aux = JOptionPane.showInputDialog("C�DIGO DO CONSULTA: ");
			if (aux != null && (!aux.equals(""))) {
				isc.setId_consulta(Integer.parseInt(aux));
				aux = JOptionPane.showInputDialog("C�DIGO DO SERVI�O: ");
				if (aux != null && (!aux.equals(""))) {
					isc.setId_servico(Integer.parseInt(aux));
					aux = JOptionPane
							.showInputDialog("Quantidade de Servi�o: ");
					if (aux != null && (!aux.equals(""))) {
						isc.setQut_servico(Integer.parseInt(aux));

						ItensServicoConsultaDAO iscdao = new ItensServicoConsultaDAO();
						isc.setPreco_total(iscdao.buscaPrecoTotal(
								isc.getId_servico(), isc.getQut_servico()));

						int resposta = JOptionPane.showConfirmDialog(
								null,isc,
								"******CADASTRAMENTO DE ITEM******",
								JOptionPane.YES_OPTION,
								JOptionPane.QUESTION_MESSAGE);
						if (resposta == 0) {
							iscdao.inserir(isc);
							JOptionPane.showMessageDialog(null,
									"CADASTRAMENTO COM SUCESSO!!!");
							break;
						}
					}
				}
			}
			JOptionPane.showMessageDialog(null, "CADASTRAMENTO CANCELADO!!!");
			break;
		}

	}

	// *************************************************
	// ***************ALTERAR ISC******************
	// *************************************************
	public void alterarISC() throws BancoDadosException {
		ItensServicoConsulta iscAntigo = new ItensServicoConsulta();
		ItensServicoConsulta iscNovo = new ItensServicoConsulta();
		while (true) {
			aux = JOptionPane.showInputDialog("C�DIGO DO CONSULTA: ");
			if (aux != null && (!aux.equals(""))) {
				iscNovo.setId_consulta(Integer.parseInt(aux));
				aux = JOptionPane.showInputDialog("C�DIGO DO SERVI�O: ");
					if (aux != null && (!aux.equals(""))) {
						iscNovo.setId_servico(Integer.parseInt(aux));
						ItensServicoConsultaDAO iscDao = new ItensServicoConsultaDAO();
						iscAntigo = iscDao.consultar(
								iscNovo.getId_consulta(),
								iscNovo.getId_servico());
						aux = JOptionPane
								.showInputDialog("QUANTIDADE DE SERVI�O ATUAL � DE: "
										+ iscAntigo.getQut_servico()
										+ "\nNOVA QUANTIDADE: ");
						if (aux != null) {
							iscNovo.setQut_servico((aux.equals(""))?iscAntigo.getQut_servico():Integer.parseInt(aux));
							ItensServicoConsultaDAO iscdao = new ItensServicoConsultaDAO();
							iscNovo.setPreco_total(iscdao.buscaPrecoTotal(
									iscNovo.getId_servico(), iscNovo.getQut_servico()));

							int resposta = JOptionPane
									.showConfirmDialog(
											null,
											"CONSULTA : "
													+ iscNovo.getId_consulta()
													+ "\nSERVI�O :"
													+ iscNovo.getId_servico()
													+ "\nA QUANTIDADE DESTE SERVI�O NA CONSULTA � DE :"
													+ iscAntigo
															.getQut_servico()
													+ "\nALTERAR PARA: "
													+ iscNovo.getQut_servico()
													+ " ?",
											"****** ATEN��O ******",
											JOptionPane.YES_OPTION,
											JOptionPane.WARNING_MESSAGE);
							if (resposta == 0) {
								iscDao.alterar(iscNovo);
								JOptionPane.showMessageDialog(null,
										"QUANTIDADE ALTERADA COM SUCESSO!!!");
								break;
							}
						}
					}
				}
			

			JOptionPane.showMessageDialog(null,
					"QUANTIDADE N�O FOI ALTERADO!!!");
			break;
		}
	}

	// *************************************************
	// ***************EXCLUIR ISC******************
	// *************************************************
	public void excluirISC() throws BancoDadosException {
		ItensServicoConsulta isc = new ItensServicoConsulta();
		int id_consulta, id_servico;
		while (true) {
			aux = JOptionPane.showInputDialog("DIGITE O C�DIGO DO CONSULTA: ");
			if (aux != null && (!aux.equals(""))) {
				id_consulta = Integer.parseInt(aux);
				aux = JOptionPane
						.showInputDialog("DIGITE O C�DIGO DO SERVI�O: ");
				if (aux != null && (!aux.equals(""))) {
					id_servico = Integer.parseInt(aux);
					ItensServicoConsultaDAO iscDao = new ItensServicoConsultaDAO();
					isc = iscDao.consultar(id_consulta, id_servico);
					int resposta = JOptionPane
							.showConfirmDialog(null,
									"Voc� deseja realmente excluir este Servi�o desta Consulta "
											+ "?\n" + isc,
									"****** ATEN��O ******",
									JOptionPane.YES_OPTION,
									JOptionPane.WARNING_MESSAGE);
					if (resposta == 0) {
						iscDao.exclui(id_consulta, id_servico);
						JOptionPane.showMessageDialog(null,
								"SERVI�O EXCLU�DO COM SUCESSO!!!");
						break;
					}
				}
			}
			JOptionPane.showMessageDialog(null, "SERVI�O N�O FOI EXCLU�DO!!!");
			break;
		}
	}

	// ***************************************************
	// ***************CONSULTAR PACIENTE******************
	// ***************************************************
	public void consultaISC() throws BancoDadosException {
		StringBuffer lista = new StringBuffer("");
		aux="";		
		do {
			lista = aux.equals("")?listarIsc():listarIsc(aux);
			String literal = lista.toString();
			JTextArea area = new JTextArea(literal);
			JScrollPane scroll = new JScrollPane(area);
			scroll.setPreferredSize(new Dimension(400, 300));
			aux = JOptionPane.showInputDialog(null, scroll,
					"CANCELAR PARA SAIR", JOptionPane.YES_NO_CANCEL_OPTION);

		} while (aux != null);
	}

	public StringBuffer listarIsc() throws BancoDadosException {
		StringBuffer lista = new StringBuffer("");
		List<ItensServicoConsulta> iscs = new ArrayList<ItensServicoConsulta>();
		ItensServicoConsultaDAO iscDao = new ItensServicoConsultaDAO();
		iscs=iscDao.consulta();
		for (ItensServicoConsulta isc : iscs) {
			lista.append(isc);
		}

		return lista;
	}
	public StringBuffer listarIsc(String aux) throws BancoDadosException {
		StringBuffer lista = new StringBuffer("");
		List<ItensServicoConsulta> iscs = new ArrayList<ItensServicoConsulta>();
		ItensServicoConsultaDAO iscDao = new ItensServicoConsultaDAO();
		iscs=iscDao.consulta(Integer.parseInt(aux));
		for (ItensServicoConsulta isc : iscs) {
			lista.append(isc);
		}

		return lista;
	}

}
