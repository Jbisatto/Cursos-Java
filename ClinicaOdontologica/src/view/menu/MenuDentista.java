package view.menu;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.DentistaDao;
import enumerador.ESexo;
import bancoDeDados.BancoDadosException;
import beans.Dentista;

public class MenuDentista {
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
									+ "\n\nCADASTRAR\nNo Botão de \"CADASTRAR\" é possivel fazer o cadastramento de novos dentistas.\n"
									+ "Cada iten que abrir tem que ser preenchido de forma correta, pois não irá cadastrar.\n"
									+ "Caso seja deixado algum campo em branco, o cadastramento será cancelado!"
									+ "\n\nEDITAR\nNo Botão de \"EDITAR\" é possivel fazer a alteração das informações do dentista.\n"
									+ "Cada iten que abrir tem que ser preenchido de forma correta, pois não irá realizar \nas alterações.\n"
									+ "Caso seja deixado algum campo em branco, a alterações irá continuar com as \ninformações anteriores"
									+ "\n\nEXCLUIR\nNo Botão de \"EXCLUIR\" é possivel excluir um dentista. Para excluir é necessário\nter "
									+ "o código do serviço, para consegui-lo clique no botão \"CONSULTAR\"!"
									+ "\n\nCONSULTAR\nNo Botão de \"CONSULTAR\" é possivel consultar todos os dentistas cadastrados.\n\n\n",
							"****************************** DENTISTA ******************************" // Title
							, JOptionPane.YES_NO_OPTION // Option type
							, JOptionPane.PLAIN_MESSAGE // messageType
							, null // Icon (none)
							, menupri // Button text as above.
							, "None of your business" // Default button's label
					);
			switch (response) {
			case 0:
				cadDentista();
				break;
			case 1:
				alterarDentista();
				break;
			case 2:
				excluirDentista();
				break;
			case 3:
				consultaDentista();
				break;

			default:
				break;
			}
		} while (response != -1 && response != 4);
	}

	// ************************************************************
	// *****************CADASTRAR DENTISTA*************************
	// *************************************************************
	public void cadDentista() throws BancoDadosException {
		Dentista dentista = new Dentista();
		while (true) {
			aux = JOptionPane.showInputDialog("NOME COMPLETO DO DENTISTA");
			if (aux != null && (!aux.equals(""))) {
				dentista.setNome(aux);
				aux = JOptionPane.showInputDialog("CPF DO DENTISTA: ");
				if (aux != null && (!aux.equals(""))) {
					dentista.setCpf(Integer.parseInt(aux));
					aux = JOptionPane.showInputDialog("TELEFONE DO DENTISTA: ");
					if (aux != null && (!aux.equals(""))) {
						dentista.setTelefone(Integer.parseInt(aux));
						aux = JOptionPane
								.showInputDialog("EMAIL DO DENTISTA: ");
						if (aux != null && (!aux.equals(""))) {
							dentista.setEmail(aux);
							aux = JOptionPane
									.showInputDialog("SEXO DO PACIENTE(M-Masculino/F-Feminino): ");
							if (aux != null && (!aux.equals(""))) {
								dentista.setSexo(aux.toUpperCase().equals("M") ? ESexo.MASCULINO
										: ESexo.FEMININO);
								aux = JOptionPane
										.showInputDialog("CRO DO DENTISTA");
								if (aux != null && (!aux.equals(""))) {
									dentista.setCro(Integer.parseInt(aux));
									int resposta = JOptionPane
											.showConfirmDialog(
													null,
													dentista,
													"******CADASTRAMENTO DE DENTISTA******",
													JOptionPane.YES_OPTION,
													JOptionPane.QUESTION_MESSAGE);
									if (resposta == 0) {
										DentistaDao dentistaDao = new DentistaDao();
										dentistaDao.insere(dentista);
										JOptionPane.showMessageDialog(null,
												"CADASTRAMENTO COM SUCESSO!!!");
										break;
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
	// *****************ALTERAR DENTISTA*************************
	// *************************************************************
	public void alterarDentista() throws BancoDadosException {
		Dentista dentistaAntigo = new Dentista();
		Dentista dentistaNovo = new Dentista();
		while (true) {

			aux = JOptionPane.showInputDialog("DIGITE O ID DO DENTISTA: ");
			if (aux != null && (!aux.equals(""))) {
				dentistaNovo.setId(Integer.parseInt(aux));
				DentistaDao dentistaDao = new DentistaDao();
				dentistaAntigo = dentistaDao.consulta(dentistaNovo.getId());
				aux = JOptionPane.showInputDialog("NOME ATUAL DO DENTISTA: "
						+ dentistaAntigo.getNome() + "\nDIGITE O NOVO NOME:");
				if (aux != null) {
					dentistaNovo.setNome(aux.equals("")?dentistaAntigo.getNome():aux);
					aux = JOptionPane.showInputDialog("CPF ATUAL DO DENTISTA: "
							+ dentistaAntigo.getCpf()
							+ "\nNOVO CPF DO DENTISTA: ");
					if (aux != null) {
						dentistaNovo.setCpf(aux.equals("")?dentistaAntigo.getCpf():Integer.parseInt(aux));
						aux = JOptionPane
								.showInputDialog("TELEFONE ATUAL DO DENTISTA: "
										+ dentistaAntigo.getTelefone()
										+ "\nNOVO TELEFONE DO DENTISTA: ");
						if (aux != null) {
							dentistaNovo.setTelefone(aux.equals("")?dentistaAntigo.getTelefone():Integer.parseInt(aux));
							aux = JOptionPane
									.showInputDialog("EMAIL ATUAL DO DENTISTA: "
											+ dentistaAntigo.getEmail()
											+ "\nNOVO EMAIL DO DENTISTA :");
							if (aux != null) {
								dentistaNovo.setEmail(aux.equals("")?dentistaAntigo.getEmail():aux);
								aux = JOptionPane
										.showInputDialog("SEXO ATUAL DO DENTISTA :"
												+ dentistaAntigo.getSexo()
												+ "\nNOVO SEXO DO DENTISTA(M-Masculino/F-Feminino): ");
								if (aux != null) {
									dentistaNovo.setSexo(aux.equals("")?dentistaAntigo.getSexo():aux.toUpperCase()
											.equals("M") ? ESexo.MASCULINO
											: ESexo.FEMININO);
									aux = JOptionPane
											.showInputDialog("CRO ATUAL DO DENTISTA: "
													+ dentistaAntigo.getCro()
													+ "\nNOVO CRO DO DENTISTA: ");
									if (aux != null) {
										dentistaNovo.setCro(aux.equals("")?dentistaAntigo.getCro():Integer
												.parseInt(aux));
										int resposta = JOptionPane
												.showConfirmDialog(
														null,
														"Você deseja realmente alterar o dentista "
																+ dentistaAntigo
																+ " para :\n"
																+ dentistaNovo
																+ " ?",
														"****** ATENÇÃO ******",
														JOptionPane.YES_OPTION,
														JOptionPane.QUESTION_MESSAGE);
										if (resposta == 0) {

											dentistaDao.altera(dentistaNovo);
											JOptionPane.showMessageDialog(null,
													"ALTERADO COM SUCESSO!!!");
											break;
										}
									}
								}
							}
						}
					}
				}
			}
			JOptionPane.showMessageDialog(null, "ALTERAÇÃO CANCELADA!!!");
			break;
		}
	}

	// *************************************************
	// ***************EXCLUIR DENTISTA******************
	// *************************************************
	public void excluirDentista() throws BancoDadosException {
		Dentista dentista = new Dentista();
		String aux;

		aux = JOptionPane.showInputDialog("DIGITE O ID DO DENTISTA: ");
		while (true) {
			if (aux != null) {
				int id_dentista = Integer.parseInt(aux);
				DentistaDao dentistaDao = new DentistaDao();
				dentista = dentistaDao.consulta(id_dentista);
				int resposta = JOptionPane.showConfirmDialog(
						null,
						"Você deseja realmente excluir o dentista "
								+ dentista+ " ?",
						"****** ATENÇÃO ******", JOptionPane.YES_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (resposta == 0) {
					
						dentistaDao.exclui(id_dentista);
					JOptionPane.showMessageDialog(null,
							"DENTISTA EXCLUÍDO COM SUCESSO!!!");
					break;
				}
			}
			JOptionPane.showMessageDialog(null, "DENTISTA NÃO FOI EXCLUÍDO!!!");
			break;
		}
	}

	// ***************************************************
	// ***************CONSULTAR PACIENTE******************
	// ***************************************************
	public void consultaDentista() throws BancoDadosException {
		StringBuffer lista = new StringBuffer("");
		aux="";
		do {
			lista = (aux.equals("")?listaDentista():listaDentista(aux));
			String literal = lista.toString();
			JTextArea area = new JTextArea(literal);
			JScrollPane scroll = new JScrollPane(area);
			scroll.setPreferredSize(new Dimension(400, 300));
			// JOptionPane.showMessageDialog(null, scroll);
			aux = JOptionPane.showInputDialog(null, scroll,
					" OK PRA BUSCAR / CANCELAR PARA SAIR", JOptionPane.YES_NO_CANCEL_OPTION);
		} while (aux != null);
	}

	public StringBuffer listaDentista() throws BancoDadosException {
		StringBuffer lista = new StringBuffer("");
		List<Dentista> dentistas = new ArrayList<Dentista>();
		DentistaDao dentistaDao = new DentistaDao();
		dentistas=dentistaDao.consulta();
		for (Dentista dentista : dentistas){
			lista.append(dentista);
		}

		return lista;
	}
	public StringBuffer listaDentista(String aux) throws BancoDadosException {
		StringBuffer lista = new StringBuffer("");
		List<Dentista> dentistas = new ArrayList<Dentista>();
		DentistaDao dentistaDao = new DentistaDao();
		dentistas=dentistaDao.consulta(aux);
		for (Dentista dentista : dentistas){
			lista.append(dentista);
		}

		return lista;
	}
}
