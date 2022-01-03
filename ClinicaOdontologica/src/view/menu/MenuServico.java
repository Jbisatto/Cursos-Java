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
							"SELECIONE A OPÇÃO DESEJADA!"
									+ "\n\nCADASTRAR\nNo Botão de \"CADASTRAR\" é possivel fazer o cadastramento de novos serviços.\n"
									+ "Cada iten que abrir tem que ser preenchido de forma correta, pois senão, não irá cadastrar.\n"
									+ "Caso seja deixado algum campo em branco, o cadastramento será cancelado!"
									+ "\n\nEDITAR\nNo Botão de \"EDITAR\" é possivel fazer a alteração das informações do serviços.\n"
									+ "Cada iten que abrir tem que ser preenchido de forma correta, pois não irá realizar \nas alterações.\n"
									+ "Caso seja deixado algum campo em branco, a alterações irá continuar com as \ninformações anteriores"
									+ "\n\nEXCLUIR\nNo Botão de \"EXCLUIR\" é possivel excluir um serviço. Para excluir é necessário\nter "
									+ "o código do serviço, para consegui-lo clique no botão \"CONSULTAR\"!"
									+ "\n\nCONSULTAR\nNo Botão de \"CONSULTAR\" é possivel consultar todos os serviço cadastrados.\n\n\n",
							"************************************ SERVIÇO ************************************" // Title
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
	// *****************CADASTRAR SERVIÇO**************************
	// ************************************************************
	public void cadServico() throws BancoDadosException {
		Servico servico = new Servico();
		while (true) {
			aux = JOptionPane.showInputDialog("NOME DO SERVIÇO");
			if (aux != null && (!aux.equals(""))) {
				servico.setNomeServico(aux);
				aux = JOptionPane.showInputDialog("PREÇO DO SERVIÇO(R$)");
				if (aux != null && (!aux.equals(""))) {
					servico.setPrecoServico(Float.parseFloat(aux));
					int resposta = JOptionPane.showConfirmDialog(null, servico,
							"******CADASTRAMENTO DE SERVIÇO******",
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
	// *****************ALTERAR SERVIÇO**************************
	// ************************************************************
	public void alteraServico() throws BancoDadosException {
		Servico servicoAntigo = new Servico();
		Servico servicoNovo = new Servico();
		while (true) {
			aux = JOptionPane.showInputDialog("CÓDIGO DO SERVIÇO");
			if (aux != null && (!aux.equals(""))) {
				int id_servico = Integer.parseInt(aux);
				servicoNovo.setId_servico(id_servico);
				ServicoDao servicoDao = new ServicoDao();
				servicoAntigo = servicoDao.consulta(id_servico);
				aux = JOptionPane.showInputDialog("NOME ATUAL DO SERVIÇO: "
						+ servicoAntigo.getNomeServico()
						+ "\nDIGITE O NOVO NOME:");
				if (aux != null) {
					servicoNovo.setNomeServico(aux.equals("")?servicoAntigo.getNomeServico():aux);
					aux = JOptionPane
							.showInputDialog("PREÇO ATUAL DO SERVIÇO R$ "
									+ servicoAntigo.getPrecoServico()
									+ "\nDIGITE O NOVO PREÇO:");
					if (aux != null) {
							servicoNovo.setPrecoServico(aux.equals("")?servicoAntigo
									.getPrecoServico():Float.parseFloat(aux));
						int resposta = JOptionPane.showConfirmDialog(null,
								"Você deseja realmente alterar o Serviço "
										+ servicoAntigo.getNomeServico()
										+ " para :\n" + servicoNovo + " ?",
								"****** ATENÇÃO ******",
								JOptionPane.YES_OPTION,
								JOptionPane.QUESTION_MESSAGE);
						if (resposta == 0) {
							servicoDao.altera(servicoNovo);
							JOptionPane.showMessageDialog(null,
									"ALTERAÇÃO COM SUCESSO!!!");
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
						"Você deseja realmente excluir o Serviço " + servico
								+ " ?", "****** ATENÇÃO ******",
						JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE);
				if (resposta == 0) {
					servicoDao.exclui(Integer.parseInt(aux));
					JOptionPane.showMessageDialog(null,
							"SERVIÇO EXCLUÍDO COM SUCESSO!!!");
					break;
				}
			}
			JOptionPane.showMessageDialog(null, "SERVIÇO NÃO FOI EXCLUÍDO!!!");
			break;
		}
	}

	// ***************************************************
	// ***************CONSULTAR SERVIÇO******************
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
