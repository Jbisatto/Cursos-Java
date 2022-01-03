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
							"SELECIONE A OPÇÃO DESEJADA!"
									+ "\n\nCADASTRAR\nNo Botão de \"CADASTRAR\" é possivel fazer o cadastramento de serviço na consulta.\n"
									+ "Cada iten que abrir tem que ser preenchido de forma correta, pois não irá cadastrar.\n"
									+ "Caso seja deixado algum campo em branco, o cadastramento será cancelado!"
									+ "\n\nEDITAR\nNo Botão de \"EDITAR\" é possivel fazer a alteração das informações do serviço na consulta.\n"
									+ "Cada iten que abrir tem que ser preenchido de forma correta, pois não irá realizar \nas alterações.\n"
									+ "Caso seja deixado algum campo em branco, a alterações irá continuar com as \ninformações anteriores"
									+ "\n\nEXCLUIR\nNo Botão de \"EXCLUIR\" é possivel excluir um serviço na consulta. Para excluir é necessário\nter "
									+ "o código do serviço, para consegui-lo clique no botão \"CONSULTAR\"!"
									+ "\n\nCONSULTAR\nNo Botão de \"CONSULTAR\" é possivel consultar todos os serviços na consulta cadastrados.\n\n\n",
							"****************************** ITENS DE SERVIÇOS ******************************" // Title
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
			aux = JOptionPane.showInputDialog("CÓDIGO DO CONSULTA: ");
			if (aux != null && (!aux.equals(""))) {
				isc.setId_consulta(Integer.parseInt(aux));
				aux = JOptionPane.showInputDialog("CÓDIGO DO SERVIÇO: ");
				if (aux != null && (!aux.equals(""))) {
					isc.setId_servico(Integer.parseInt(aux));
					aux = JOptionPane
							.showInputDialog("Quantidade de Serviço: ");
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
			aux = JOptionPane.showInputDialog("CÓDIGO DO CONSULTA: ");
			if (aux != null && (!aux.equals(""))) {
				iscNovo.setId_consulta(Integer.parseInt(aux));
				aux = JOptionPane.showInputDialog("CÓDIGO DO SERVIÇO: ");
					if (aux != null && (!aux.equals(""))) {
						iscNovo.setId_servico(Integer.parseInt(aux));
						ItensServicoConsultaDAO iscDao = new ItensServicoConsultaDAO();
						iscAntigo = iscDao.consultar(
								iscNovo.getId_consulta(),
								iscNovo.getId_servico());
						aux = JOptionPane
								.showInputDialog("QUANTIDADE DE SERVIÇO ATUAL É DE: "
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
													+ "\nSERVIÇO :"
													+ iscNovo.getId_servico()
													+ "\nA QUANTIDADE DESTE SERVIÇO NA CONSULTA É DE :"
													+ iscAntigo
															.getQut_servico()
													+ "\nALTERAR PARA: "
													+ iscNovo.getQut_servico()
													+ " ?",
											"****** ATENÇÃO ******",
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
					"QUANTIDADE NÃO FOI ALTERADO!!!");
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
			aux = JOptionPane.showInputDialog("DIGITE O CÓDIGO DO CONSULTA: ");
			if (aux != null && (!aux.equals(""))) {
				id_consulta = Integer.parseInt(aux);
				aux = JOptionPane
						.showInputDialog("DIGITE O CÓDIGO DO SERVIÇO: ");
				if (aux != null && (!aux.equals(""))) {
					id_servico = Integer.parseInt(aux);
					ItensServicoConsultaDAO iscDao = new ItensServicoConsultaDAO();
					isc = iscDao.consultar(id_consulta, id_servico);
					int resposta = JOptionPane
							.showConfirmDialog(null,
									"Você deseja realmente excluir este Serviço desta Consulta "
											+ "?\n" + isc,
									"****** ATENÇÃO ******",
									JOptionPane.YES_OPTION,
									JOptionPane.WARNING_MESSAGE);
					if (resposta == 0) {
						iscDao.exclui(id_consulta, id_servico);
						JOptionPane.showMessageDialog(null,
								"SERVIÇO EXCLUÍDO COM SUCESSO!!!");
						break;
					}
				}
			}
			JOptionPane.showMessageDialog(null, "SERVIÇO NÃO FOI EXCLUÍDO!!!");
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
