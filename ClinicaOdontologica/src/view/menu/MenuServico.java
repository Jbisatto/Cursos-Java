package view.menu;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.ServicoDao;
import bancoDeDados.BancoDadosException;
import beans.Servico;

public class MenuServico {
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
									+ "\n\nCADASTRAR\nNo Bot�o de \"CADASTRAR\" � possivel fazer o cadastramento de novos servi�os.\n"
									+ "Cada iten que abrir tem que ser preenchido de forma correta, pois sen�o, n�o ir� cadastrar.\n"
									+ "Caso seja deixado algum campo em branco, o cadastramento ser� cancelado!"
									+ "\n\nEDITAR\nNo Bot�o de \"EDITAR\" � possivel fazer a altera��o das informa��es do servi�os.\n"
									+ "Cada iten que abrir tem que ser preenchido de forma correta, pois n�o ir� realizar \nas altera��es.\n"
									+ "Caso seja deixado algum campo em branco, a altera��es ir� continuar com as \ninforma��es anteriores"
									+ "\n\nEXCLUIR\nNo Bot�o de \"EXCLUIR\" � possivel excluir um servi�o. Para excluir � necess�rio\nter "
									+ "o c�digo do servi�o, para consegui-lo clique no bot�o \"CONSULTAR\"!"
									+ "\n\nCONSULTAR\nNo Bot�o de \"CONSULTAR\" � possivel consultar todos os servi�o cadastrados.\n\n\n",
							"************************************ SERVI�O ************************************" // Title
							, JOptionPane.YES_NO_OPTION // Option type
							, JOptionPane.PLAIN_MESSAGE // messageType
							, null // Icon (none)
							, menupri // Button text as above.
							, "None of your business" // Default button's label
					);
			switch (response) {
			case 0:
				cadServico();

				break;
			case 1:
				alteraServico();
				break;
			case 2:
				excluirServico();
				break;
			case 3:
				consultaServico();
				break;

			default:
				break;
			}
		} while (response != -1 && response != 4);
	}

	// ************************************************************
	// *****************CADASTRAR SERVI�O**************************
	// ************************************************************
	public void cadServico() throws BancoDadosException {
		Servico servico = new Servico();
		while (true) {
			aux = JOptionPane.showInputDialog("NOME DO SERVI�O");
			if (aux != null && (!aux.equals(""))) {
				servico.setNomeServico(aux);
				aux = JOptionPane.showInputDialog("PRE�O DO SERVI�O(R$)");
				if (aux != null && (!aux.equals(""))) {
					servico.setPrecoServico(Float.parseFloat(aux));
					int resposta = JOptionPane.showConfirmDialog(null, servico,
							"******CADASTRAMENTO DE SERVI�O******",
							JOptionPane.YES_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (resposta == 0) {
						JOptionPane.showMessageDialog(null,
								"CADASTRAMENTO COM SUCESSO!!!");
						ServicoDao servicoDao = new ServicoDao();
						servicoDao.insere(servico);
						break;
					}
				}
			}
			JOptionPane.showMessageDialog(null, "CADASTRAMENTO CANCELADO!!!");
			break;
		}
	}

	// ************************************************************
	// *****************ALTERAR SERVI�O**************************
	// ************************************************************
	public void alteraServico() throws BancoDadosException {
		Servico servicoAntigo = new Servico();
		Servico servicoNovo = new Servico();
		while (true) {
			aux = JOptionPane.showInputDialog("C�DIGO DO SERVI�O");
			if (aux != null && (!aux.equals(""))) {
				int id_servico = Integer.parseInt(aux);
				servicoNovo.setId_servico(id_servico);
				ServicoDao servicoDao = new ServicoDao();
				servicoAntigo = servicoDao.consulta(id_servico);
				aux = JOptionPane.showInputDialog("NOME ATUAL DO SERVI�O: "
						+ servicoAntigo.getNomeServico()
						+ "\nDIGITE O NOVO NOME:");
				if (aux != null) {
					servicoNovo.setNomeServico(aux.equals("")?servicoAntigo.getNomeServico():aux);
					aux = JOptionPane
							.showInputDialog("PRE�O ATUAL DO SERVI�O R$ "
									+ servicoAntigo.getPrecoServico()
									+ "\nDIGITE O NOVO PRE�O:");
					if (aux != null) {
							servicoNovo.setPrecoServico(aux.equals("")?servicoAntigo
									.getPrecoServico():Float.parseFloat(aux));
						int resposta = JOptionPane.showConfirmDialog(null,
								"Voc� deseja realmente alterar o Servi�o "
										+ servicoAntigo.getNomeServico()
										+ " para :\n" + servicoNovo + " ?",
								"****** ATEN��O ******",
								JOptionPane.YES_OPTION,
								JOptionPane.QUESTION_MESSAGE);
						if (resposta == 0) {
							servicoDao.altera(servicoNovo);
							JOptionPane.showMessageDialog(null,
									"ALTERA��O COM SUCESSO!!!");
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
	// ***************EXCLUIR SERVICO******************
	// *************************************************
	public void excluirServico() throws BancoDadosException {
		Servico servico = new Servico();
		while (true) {
			aux = JOptionPane.showInputDialog("DIGITE O ID DO PACIENTE: ");
			if (aux != null && (!aux.equals(""))) {
				ServicoDao servicoDao = new ServicoDao();
				servico = servicoDao.consulta(Integer.parseInt(aux));
				int resposta = JOptionPane.showConfirmDialog(null,
						"Voc� deseja realmente excluir o Servi�o " + servico
								+ " ?", "****** ATEN��O ******",
						JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE);
				if (resposta == 0) {
					servicoDao.exclui(Integer.parseInt(aux));
					JOptionPane.showMessageDialog(null,
							"SERVI�O EXCLU�DO COM SUCESSO!!!");
					break;
				}
			}
			JOptionPane.showMessageDialog(null, "SERVI�O N�O FOI EXCLU�DO!!!");
			break;
		}
	}

	// ***************************************************
	// ***************CONSULTAR SERVI�O******************
	// ***************************************************
	public void consultaServico() throws BancoDadosException {
		StringBuffer lista = new StringBuffer("");
		aux="";

		do {
			lista = (aux.equals("")?listarServico():listarServico(aux));
			String literal = lista.toString();
			JTextArea area = new JTextArea(literal);
			JScrollPane scroll = new JScrollPane(area);
			scroll.setPreferredSize(new Dimension(400, 300));
			aux = JOptionPane.showInputDialog(null, scroll,
					" OK PRA BUSCAR / CANCELAR PARA SAIR", JOptionPane.YES_NO_CANCEL_OPTION);
		} while (aux != null);
	}

	public StringBuffer listarServico() throws BancoDadosException {
		List<Servico> servicos = new ArrayList<Servico>();
		StringBuffer lista = new StringBuffer("");
		ServicoDao servicoDao = new ServicoDao();
		servicos = servicoDao.consulta();
		for (Servico servico : servicos) {
			lista.append(servico);
		}

		return lista;
	}
	public StringBuffer listarServico(String aux) throws BancoDadosException {
		List<Servico> servicos = new ArrayList<Servico>();
		StringBuffer lista = new StringBuffer("");
		ServicoDao servicoDao = new ServicoDao();
		servicos = servicoDao.consulta(aux);
		for (Servico servico : servicos) {
			lista.append(servico);
		}

		return lista;
	}
}
