package view.menu;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.FuncionarioDao;
import enumerador.ESexo;
import enumerador.ETipo;
import bancoDeDados.BancoDadosException;
import beans.Funcionario;

public class MenuFuncionario {
	String aux;

	public void String() throws HeadlessException, BancoDadosException {
		int response = 0;

		do {

			String[] menupri = { "CADASTRAR", "EDITAR", "EXCLUIR", "CONSULTAR",
					"SAIR" };
			response = JOptionPane
					.showOptionDialog(
							null // Center in window.
							,
							"SELECIONE A OPÇÃO DESEJADA!"
									+ "\n\nCADASTRAR\nNo Botão de \"CADASTRAR\" é possivel fazer o cadastramento de novos funcionários.\n"
									+ "Cada iten que abrir tem que ser preenchido de forma correta, pois não irá cadastrar.\n"
									+ "Caso seja deixado algum campo em branco, o cadastramento será cancelado!"
									+ "\n\nEDITAR\nNo Botão de \"EDITAR\" é possivel fazer a alteração das informações do funcionário.\n"
									+ "Cada iten que abrir tem que ser preenchido de forma correta, pois não irá realizar \nas alterações.\n"
									+ "Caso seja deixado algum campo em branco, a alterações irá continuar com as \ninformações anteriores"
									+ "\n\nEXCLUIR\nNo Botão de \"EXCLUIR\" é possivel excluir um funcionários. Para excluir é necessário\nter "
									+ "o código do serviço, para consegui-lo clique no botão \"CONSULTAR\"!"
									+ "\n\nCONSULTAR\nNo Botão de \"CONSULTAR\" é possivel consultar todos os funcionários cadastrados.\n\n\n",
							"****************************** FUNCIONÁRIO ******************************" // Title
							, JOptionPane.YES_NO_OPTION // Option type
							, JOptionPane.PLAIN_MESSAGE // messageType
							, null // Icon (none)
							, menupri // Button text as above.
							, "None of your business" // Default button's label
					);
			switch (response) {
			case 0:
				cadFuncionario();
				break;
			case 1:
				alterarFuncionario();
				break;
			case 2:
				excluirFuncionario();
				break;
			case 3:
				consultaFuncionario();
				break;

			default:
				break;
			}
		} while (response != -1 && response != 4);
	}

	// ************************************************************
	// *****************CADASTRAR FUNCIONÁRIO**********************
	// ************************************************************
	public void cadFuncionario() throws HeadlessException, BancoDadosException {
		Funcionario funcionario = new Funcionario();
		while (true) {
			aux = JOptionPane.showInputDialog("NOME COMPLETO DO FUNCIONÁRIO: ");

			if (aux != null && (!aux.equals(""))) {
				funcionario.setNome(aux);
				aux = JOptionPane.showInputDialog("CPF DO FUNCIONÁRIO: ");
				if (aux != null && (!aux.equals(""))) {
					funcionario.setCpf(Integer.parseInt(aux));
					aux = JOptionPane
							.showInputDialog("TELEFONE DO FUNCIONÁRIO: ");
					if (aux != null && (!aux.equals(""))) {
						funcionario.setTelefone(Integer.parseInt(aux));
						aux = JOptionPane
								.showInputDialog("EMAIL DO FUNCIONÁRIO: ");
						if (aux != null && (!aux.equals(""))) {
							funcionario.setEmail(aux);
							aux = JOptionPane
									.showInputDialog("SEXO DO FUNCIONÁRIO(M-Masculino/F-Feminino): ");
							if (aux != null && (!aux.equals(""))) {
								funcionario
										.setSexo(aux.toUpperCase().equals("M") ? ESexo.MASCULINO
												: ESexo.FEMININO);
								aux = JOptionPane
										.showInputDialog("TIPO DE FUNCIONÁRIO\n\tS-SECRETÁRIA\n\tA-ADMINISTRADOR");
								if (aux != null && (!aux.equals(""))) {
									funcionario.setTipo(aux.toUpperCase()
											.equals("S") ? ETipo.SECRETARIA
											: ETipo.ADMINISTRADOR);
									int resposta = JOptionPane
											.showConfirmDialog(
													null,
													funcionario,
													"******CADASTRAMENTO DE FUNCIONÁRIO******",
													JOptionPane.YES_OPTION,
													JOptionPane.QUESTION_MESSAGE);
									if (resposta == 0) {
										FuncionarioDao funcionarioDao = new FuncionarioDao();

										if (funcionarioDao.insere(funcionario)) {
											JOptionPane
													.showMessageDialog(null,
															"CADASTRAMENTO COM SUCESSO!!!");
											break;
										}
									}
								}
							}
						}
					}
				}
			}
			JOptionPane.showMessageDialog(null, "CADASTRAMENTO CANCELADO!!!");
			break;
		}
	}

	// ************************************************************
	// *****************ALTERAR FUNCIONÁRIO**********************
	// ************************************************************
	public void alterarFuncionario() throws HeadlessException,
			BancoDadosException {
		Funcionario funcionarioAntigo = new Funcionario();
		Funcionario funcionarioNovo = new Funcionario();
		while (true) {

			aux = JOptionPane.showInputDialog("DIGITE O ID DO FUNCIONÁRIO: ");
			if (aux != null && (!aux.equals(""))) {
				funcionarioNovo.setId(Integer.parseInt(aux));
				FuncionarioDao funcionarioDao = new FuncionarioDao();
				funcionarioAntigo = funcionarioDao.consulta(funcionarioNovo
						.getId());
				aux = JOptionPane
						.showInputDialog("NOME ATUAL DO FUNCIONÁRIO: "
								+ funcionarioAntigo.getNome()
								+ "\nDIGITE O NOVO NOME:");
				if (aux != null) {
					funcionarioNovo
							.setNome((aux.equals("")) ? funcionarioAntigo
									.getNome() : aux);
					aux = JOptionPane
							.showInputDialog("CPF ATUAL DO FUNCIONÁRIO: "
									+ funcionarioAntigo.getCpf()
									+ "\nNOVO CPF DO FUNCIONÁRIO: ");
					if (aux != null) {
						funcionarioNovo
								.setCpf((aux.equals("")) ? funcionarioAntigo
										.getCpf() : Integer.parseInt(aux));
						aux = JOptionPane
								.showInputDialog("TELEFONE ATUAL DO FUNCIONÁRIO: "
										+ funcionarioAntigo.getTelefone()
										+ "\nNOVO TELEFONE DO FUNCIONÁRIO: ");
						if (aux != null) {
							funcionarioNovo
									.setTelefone((aux.equals("")) ? funcionarioAntigo
											.getTelefone() : Integer
											.parseInt(aux));
							aux = JOptionPane
									.showInputDialog("EMAIL ATUAL DO FUNCIONÁRIO: "
											+ funcionarioAntigo.getEmail()
											+ "\nNOVO EMAIL DO FUNCIONÁRIO: ");
							if (aux != null) {
								funcionarioNovo
										.setEmail((aux.equals("")) ? funcionarioAntigo
												.getEmail() : aux);
								aux = JOptionPane
										.showInputDialog("SEXO ATUAL DO FUNCIONÁRIO: "
												+ funcionarioAntigo.getSexo()
												+ "\nNOVO SEXO DO FUNCIONÁRIO(M-Masculino/F-Feminino): ");
								if (aux != null) {
									funcionarioNovo
											.setSexo(aux.equals("") ? funcionarioAntigo
													.getSexo()
													: (aux.toUpperCase()
															.equals("M") ? ESexo.MASCULINO
															: ESexo.FEMININO));
									aux = JOptionPane
											.showInputDialog("TIPO ATUAL DE FUNCIONÁRIO: "
													+ funcionarioAntigo
															.getTipo()
													+ "\nNOVO TIPO DE FUNCIONÁRIO(S-SECRETÁRIA/A-ADMINISTRADOR): ");
									if (aux != null) {
										funcionarioNovo
												.setTipo(aux.equals("") ? funcionarioAntigo
														.getTipo()
														: aux.toUpperCase()
																.equals("S") ? ETipo.SECRETARIA
																: ETipo.ADMINISTRADOR);
										int resposta = JOptionPane
												.showConfirmDialog(
														null,
														"Você deseja realmente alterar o funcionário"
																+ funcionarioAntigo
																+ " para :\n"
																+ funcionarioNovo,
														"******ATENÇÃO******",
														JOptionPane.YES_OPTION,
														JOptionPane.QUESTION_MESSAGE);
										if (resposta == 0) {
											funcionarioDao
													.alterarFuncionario(funcionarioNovo);
											JOptionPane
													.showMessageDialog(null,
															"FUNCIONÁRIO ALTERADO COM SUCESSO!!!");
											break;
										}
									}
								}
							}
						}
					}
				}
			}
			JOptionPane.showMessageDialog(null,
					"FUNCIONÁRIO NÃO FOI ALTERADO!!!");
			break;
		}
	}

	// *************************************************
	// ***************EXCLUIR FUNCIONÁRIO***************
	// *************************************************
	public void excluirFuncionario() throws BancoDadosException {
		Funcionario funcionario = new Funcionario();
		int id_funcionario;
		while (true) {

			aux = JOptionPane.showInputDialog("DIGITE O ID DO FUNCIONÁRIO: ");
			if (aux != null && (!aux.equals(""))) {
				id_funcionario = Integer.parseInt(aux);
				FuncionarioDao funcionarioDao = new FuncionarioDao();
				funcionario = funcionarioDao.consulta(id_funcionario);
				int resposta = JOptionPane.showConfirmDialog(null,
						"Você deseja realmente excluir o funcionário "
								+ funcionario + " ?", "****** ATENÇÃO ******",
						JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE);
				if (resposta == 0) {
					funcionarioDao.excluirFuncionario(id_funcionario);
					JOptionPane.showMessageDialog(null,
							"FUNCIONÁRIO EXCLUÍDO COM SUCESSO!!!");
					break;
				}
			}
			JOptionPane.showMessageDialog(null,
					"FUNCIONÁRIO NÃO FOI EXCLUÍDO!!!");
			break;
		}
	}

	public void consultaFuncionario() throws BancoDadosException {
		String aux;
		StringBuffer lista = new StringBuffer("");
		aux="";
		do {
			lista = (aux.equals("")?listaFuncionario():listaFuncionarioNome(aux));
			String literal = lista.toString();
			JTextArea area = new JTextArea(literal);
			JScrollPane scroll = new JScrollPane(area);
			scroll.setPreferredSize(new Dimension(400, 300));
			// JOptionPane.showMessageDialog(null, scroll);
			aux = JOptionPane.showInputDialog(null, scroll,
					" OK PRA BUSCAR / CANCELAR PARA SAIR", JOptionPane.YES_NO_CANCEL_OPTION);

		} while (aux != null);
	}

	public StringBuffer listaFuncionario() throws BancoDadosException {
		StringBuffer lista = new StringBuffer("");
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		FuncionarioDao funcionarioDao = new FuncionarioDao();
		funcionarios=funcionarioDao.consulta();
		for (Funcionario funcionario:funcionarios) {
			lista.append(funcionario);
		}

		return lista;
	}
	public StringBuffer listaFuncionarioNome(String aux) throws BancoDadosException {
		StringBuffer lista = new StringBuffer("");
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		FuncionarioDao funcionarioDao = new FuncionarioDao();
		funcionarios=funcionarioDao.consulta(aux);
		for (Funcionario funcionario:funcionarios) {
			lista.append(funcionario);
		}

		return lista;
	}
}
