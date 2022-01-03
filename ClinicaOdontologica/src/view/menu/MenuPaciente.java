package view.menu;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import bancoDeDados.BancoDadosException;
import beans.Agendamento;
import beans.Consulta;
import beans.Dentista;
import beans.Paciente;
import beans.Pagamento;
import dao.AgendamentoDao;
import dao.ConsultaDao;
import dao.DentistaDao;
import dao.PacienteDao;
import enumerador.EPagamento;
import enumerador.ESexo;

public class MenuPaciente {
	
	private String aux;

	public void String() throws BancoDadosException, ParseException {
		int response = 0;
		
		do {

			String[] menupri = { "CADASTRAR", "EDITAR", "EXCLUIR", "CONSULTAR",
					"PAGAMENTO", "SAIR" };
			response = JOptionPane
					.showOptionDialog(
							null // Center in window.
							,
							"SELECIONE A OP��O DESEJADA!"
									+ "\n\nCADASTRAR\nNo Bot�o de \"CADASTRAR\" � possivel fazer o cadastramento de novos pacientes.\n"
									+ "Cada iten que abrir tem que ser preenchido de forma correta, pois sen�o, n�o ir� cadastrar.\n"
									+ "Caso seja deixado algum campo em branco, o cadastramento ser� cancelado!"
									+ "\n\nEDITAR\nNo Bot�o de \"EDITAR\" � possivel fazer a altera��o das informa��es do paciente.\n"
									+ "Cada iten que abrir tem que ser preenchido de forma correta, pois n�o ir� realizar \nas altera��es.\n"
									+ "Caso seja deixado algum campo em branco, a altera��es ir� continuar com as \ninforma��es anteriores"
									+ "\n\nEXCLUIR\nNo Bot�o de \"EXCLUIR\" � possivel excluir um paciente. Para excluir � necess�rio\nter "
									+ "o c�digo do paciente, para consegui-lo clique no bot�o \"CONSULTAR\"!"
									+ "\n\nCONSULTAR\nNo Bot�o de \"CONSULTAR\" � possivel consultar todos os pacientes cadastrados."
									+ "\n\nPAGAMENTO\nNo Bot�o de \"PAGAMENTO\" � possivel efetuar o pagamento da consulta.\n\n\n",
							"****************************** PACIENTE ******************************",
							JOptionPane.YES_NO_OPTION // Option type
							, JOptionPane.PLAIN_MESSAGE // messageType
							, null // Icon (none)
							, menupri // Button text as above.
							, "None of your business" // Default button's label
					);
			switch (response) {
			case 0:
				cadPaciente();
				break;
			case 1:
				alterarPaciente();
				break;
			case 2:
				excluirPaciente();
				break;
			case 3:
				consultaPaciente();
				break;
			case 4:
				pagamento();
				break;
			default:
				break;
			}
		} while (response != -1 && response != 5);
	}

	// ************************************************************
	// *****************CADASTRAR PACIENTE*************************
	// ************************************************************
	public void cadPaciente() throws HeadlessException, BancoDadosException,
			ParseException {
		Paciente paciente = new Paciente();

		while (true) {
			aux = JOptionPane.showInputDialog("NOME COMPLETO DO PACIENTE: ");
			if (aux != null && (!aux.equals(""))) {
				paciente.setNome(aux);
				aux = JOptionPane.showInputDialog("CPF DO PACIENTE: ");
				if (aux != null && (!aux.equals(""))) {
					paciente.setCpf(Integer.parseInt(aux));
					aux = JOptionPane.showInputDialog("TELEFONE DO PACIENTE: ");
					if (aux != null && (!aux.equals(""))) {
						paciente.setTelefone(Integer.parseInt(aux));
						aux = JOptionPane
								.showInputDialog("EMAIL DO PACIENTE: ");
						if (aux != null && (!aux.equals(""))) {
							paciente.setEmail(aux);
							aux = JOptionPane
									.showInputDialog("SEXO DO PACIENTE(M-Masculino/F-Feminino): ");
							if (aux != null && (!aux.equals(""))) {
								paciente.setSexo(aux.toUpperCase().equals("M") ? ESexo.MASCULINO
										: ESexo.FEMININO);
								aux = JOptionPane
										.showInputDialog("CONVENIO DO PACIENTE: ");
								if (aux != null && (!aux.equals(""))) {
									paciente.setConvenio(aux);

									paciente.setDt_nascimento(cadData());
									int resposta = JOptionPane
											.showConfirmDialog(
													null,
													paciente,
													"******CADASTRAMENTO DE PACIENTE******",
													JOptionPane.YES_OPTION,
													JOptionPane.QUESTION_MESSAGE);
									if (resposta == 0) {
										PacienteDao pacienteDao = new PacienteDao();
										if (pacienteDao.insere(paciente)) {
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

	public Date cadData() throws ParseException {
		int dia, mes, ano;
		Calendar data = Calendar.getInstance();
		aux = JOptionPane
				.showInputDialog("Data de Nascimento do Paciente( Dia)");
		dia = ((aux != null && !aux.equals("")) ? Integer.parseInt(aux) : 00);
		aux = JOptionPane
				.showInputDialog("Data de Nascimento do Paciente( M�s)");
		mes = ((aux != null && !aux.equals("")) ? (Integer.parseInt(aux) - 1)
				: 00);
		aux = JOptionPane
				.showInputDialog("Data de Nascimento do Paciente( Ano)");

		ano = ((aux != null && !aux.equals("")) ? Integer.parseInt(aux) : 0000);
		data.set(ano, mes, dia);

		return data.getTime();

	}

	// *************************************************
	// ***************ALTERAR PACIENTE******************
	// *************************************************
	public void alterarPaciente() throws BancoDadosException, ParseException {
		Paciente pacienteAntigo = new Paciente();
		Paciente pacienteNovo = new Paciente();
		while (true) {

			aux = JOptionPane.showInputDialog("DIGITE O ID DO PACIENTE: ");
			System.out.println(aux);
			if (aux != null && (!aux.equals(""))) {
				pacienteNovo.setId(Integer.parseInt(aux));
				PacienteDao pacienteDao = new PacienteDao();
				pacienteAntigo = pacienteDao.consulta(pacienteNovo.getId());
				aux = JOptionPane.showInputDialog("NOME ATUAL DO PACIENTE: "
						+ pacienteAntigo.getNome() + "\nDIGITE O NOVO NOME:");
				if (aux != null) {
					pacienteNovo.setNome((aux.equals("")) ? pacienteAntigo
							.getNome() : aux);
					aux = JOptionPane.showInputDialog("CPF ATUAL DO PACIENTE: "
							+ pacienteAntigo.getCpf()
							+ "\nNOVO CPF DO PACIENTE: ");
					if (aux != null) {
						pacienteNovo.setCpf(aux.equals("") ? pacienteAntigo
								.getCpf() : Integer.parseInt(aux));
						aux = JOptionPane
								.showInputDialog("TELEFONE ATUAL DO PACIENTE: "
										+ pacienteAntigo.getTelefone()
										+ "\nNOVO TELEFONE DO PACIENTE: ");
						if (aux != null) {
							pacienteNovo
									.setTelefone(aux.equals("") ? pacienteAntigo
											.getTelefone() : Integer
											.parseInt(aux));
							aux = JOptionPane
									.showInputDialog("EMAIL ATUAL DO PACIENTE: "
											+ pacienteAntigo.getEmail()
											+ "\nNOVO EMAIL DO PACIENTE :");
							if (aux != null) {
								pacienteNovo
										.setEmail(aux.equals("") ? pacienteAntigo
												.getEmail() : aux);
								aux = JOptionPane
										.showInputDialog("SEXO ATUAL DO PACIENTE :"
												+ pacienteAntigo.getSexo()
												+ "\nNOVO SEXO DO PACIENTE(M-Masculino/F-Feminino): ");
								if (aux != null) {
									pacienteNovo
											.setSexo(aux.equals("") ? pacienteAntigo
													.getSexo()
													: (aux.toUpperCase()
															.equals("M") ? ESexo.MASCULINO
															: ESexo.FEMININO));
									aux = JOptionPane
											.showInputDialog("CONVENIO ATUAL DO PACIENTE: "
													+ pacienteAntigo
															.getConvenio()
													+ "\nNOVO CONVENIO DO PACIENTE: ");
									if (aux != null) {
										pacienteNovo
												.setConvenio(aux.equals("") ? pacienteAntigo
														.getConvenio() : aux);
										pacienteNovo
												.setDt_nascimento(alteraData(pacienteAntigo
														.getDt_nascimento()));

										int resposta = JOptionPane
												.showConfirmDialog(
														null,
														"Voc� deseja realmente alterar o paciente "
																+ pacienteAntigo
																+ " para :\n"
																+ pacienteNovo
																+ " ?",
														"****** ATEN��O ******",
														JOptionPane.YES_OPTION,
														JOptionPane.WARNING_MESSAGE);
										if (resposta == 0) {
											pacienteDao
													.alterarPaciente(pacienteNovo);
											JOptionPane
													.showMessageDialog(null,
															"PACIENTE ALTERADO COM SUCESSO!!!");
											break;
										}
									}
								}
							}
						}
					}
				}
			}
			JOptionPane.showMessageDialog(null, "PACIENTE N�O FOI ALTERADO!!!");
			break;
		}
	}

	public Date alteraData(Date dt_nascimento) throws ParseException {
		int dia, mes, ano;
		Calendar calendarioAntigo = Calendar.getInstance();
		Calendar calendarioNovo = Calendar.getInstance();
		calendarioAntigo.setTime(dt_nascimento);
		aux = JOptionPane
				.showInputDialog("DATA DE NASCIMENTO ATUAL DO PACIENTE (DIA) � "
						+ calendarioAntigo.get(Calendar.DAY_OF_MONTH)
						+ "\nNOVA DATA DE NASCIMENTO DO PACIENTE (DIA)");
		dia = ((aux != null && !aux.equals("")) ? Integer.parseInt(aux)
				: calendarioAntigo.get(Calendar.DAY_OF_MONTH));
		aux = JOptionPane
				.showInputDialog("DATA DE NASCIMENTO ATUAL DO PACIENTE (M�S) � :"
						+ (calendarioAntigo.get(Calendar.MONTH) + 1)
						+ "\nNOVA DATA DE NASCIMENTO DO PACIENTE (M�S):");
		mes = ((aux != null && !aux.equals("")) ? (Integer.parseInt(aux) - 1)
				: (calendarioAntigo.get(Calendar.MONTH)));
		aux = JOptionPane
				.showInputDialog("DATA DE NASCIMENTO ATUAL DO PACIENTE (ANO) � :"
						+ calendarioAntigo.get(Calendar.YEAR)
						+ "\nNOVA DATA DE NASCIMENTO DO PACIENTE (ANO) :");
		ano = ((aux != null && !aux.equals("")) ? Integer.parseInt(aux)
				: calendarioAntigo.get(Calendar.YEAR));
		calendarioNovo.set(ano, mes, dia);
		return calendarioNovo.getTime();

	}

	// *************************************************
	// ***************EXCLUIR PACIENTE******************
	// *************************************************
	public void excluirPaciente() throws BancoDadosException {
		Paciente paciente = new Paciente();
		int id_paciente;
		while (true) {
			aux = JOptionPane.showInputDialog("DIGITE O ID DO PACIENTE: ");
			if (aux != null && (!aux.equals(""))) {
				id_paciente = Integer.parseInt(aux);
				PacienteDao pacienteDao = new PacienteDao();
				paciente = pacienteDao.consulta(id_paciente);
				int resposta = JOptionPane.showConfirmDialog(null,
						"Voc� deseja realmente excluir o paciente " + paciente
								+ " ?", "****** ATEN��O ******",
						JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE);
				if (resposta == 0) {
					pacienteDao.excluirPaciente(id_paciente);
					JOptionPane.showMessageDialog(null,
							"PACIENTE EXCLU�DO COM SUCESSO!!!");
					break;
				}
			}
			JOptionPane.showMessageDialog(null, "PACIENTE N�O FOI EXCLU�DO!!!");
			break;
		}
	}

	// ***************************************************
	// ***************CONSULTAR PACIENTE******************
	// ***************************************************
	public void consultaPaciente() throws BancoDadosException {
		StringBuffer lista = new StringBuffer("");
		aux = "";
		do {

			lista = (aux.equals("") ? listaPaciente() : listaPacienteNome(aux));
			String literal = lista.toString();
			JTextArea area = new JTextArea(literal);
			JScrollPane scroll = new JScrollPane(area);
			scroll.setPreferredSize(new Dimension(400, 300));
			aux = JOptionPane.showInputDialog(null, scroll,
					" OK PRA BUSCAR / CANCELAR PARA SAIR", JOptionPane.YES_NO_CANCEL_OPTION);

		} while (aux != null);

	}

	public StringBuffer listaPaciente() throws BancoDadosException {
		List<Paciente> pacientes = new ArrayList<Paciente>();
		StringBuffer lista = new StringBuffer("");
		PacienteDao pacienteDao = new PacienteDao();
		pacientes = pacienteDao.consulta();
		for (Paciente paciente : pacientes) {
			lista.append(paciente);
		}

		return lista;

	}

	public StringBuffer listaPacienteNome(String aux)
			throws BancoDadosException {
		List<Paciente> pacientes = new ArrayList<Paciente>();
		StringBuffer lista = new StringBuffer("");
		PacienteDao pacienteDao = new PacienteDao();
		pacientes = pacienteDao.consulta(aux);
		for (Paciente paciente : pacientes) {
			lista.append(paciente);
		}

		return lista;

	}

	// ***************************************************
	// ***************CONSULTAR AGENDAMENTO******************
	// ***************************************************
	public String agendamentoPaciente(int paciente, String frase)
			throws BancoDadosException {
		StringBuffer lista = new StringBuffer("");

		lista = listaAgendamento(paciente);
		String literal = lista.toString();
		JTextArea area = new JTextArea(literal);
		JScrollPane scroll = new JScrollPane(area);
		scroll.setPreferredSize(new Dimension(400, 300));
		aux = JOptionPane.showInputDialog(null, scroll, frase,
				JOptionPane.YES_NO_CANCEL_OPTION);

		return aux;
	}

	public StringBuffer listaAgendamento(int paciente)
			throws BancoDadosException {
		List<Agendamento> agendamentos = new ArrayList<Agendamento>();
		StringBuffer lista = new StringBuffer("");
		AgendamentoDao agendamentoDao = new AgendamentoDao();
		agendamentos = agendamentoDao.consultaPaciente(paciente);

		for (Agendamento agendamento : agendamentos) {
			lista.append(agendamento);
		}
		return lista;
	}

	// ***************************************************
	// ***************PAGAMENTO PACIENTE******************
	// ***************************************************
	public void pagamento() throws BancoDadosException {
		MenuConsulta menuConsulta = new MenuConsulta();
		Paciente paciente = new Paciente();
		PacienteDao pacienteDao = new PacienteDao();
		Dentista dentista = new Dentista();
		DentistaDao dentistaDao = new DentistaDao();
		Agendamento agendemento = new Agendamento();
		AgendamentoDao agendementoDao = new AgendamentoDao();
		Consulta consulta = new Consulta();
		ConsultaDao consultaDao = new ConsultaDao();
		Pagamento pagamento = new Pagamento();
		while (true) {
			aux = JOptionPane.showInputDialog("DIGITE O C�DIGO DO PACIENTE: ");
			if (aux != null && !aux.equals("")) {
				int aux2 = menuConsulta.consultaConsulta(
						1,
						agendamentoPaciente((Integer.parseInt(aux)),
								"DIGITE O ID DO AGENDAMENTO"),
						"DIGITE O ID DO CONSULTA");
				if (aux2 != 0) {
					consulta = consultaDao.consulta(aux2);
					agendemento = agendementoDao.consulta(consulta
							.getId_agendamento());
					paciente = pacienteDao.consulta(agendemento
							.getId_paciente());
					dentista = dentistaDao.consulta(agendemento
							.getId_dentista());
					pagamento.setId_consulta(consulta.getId_consulta());
					pagamento.setNome_dentista(dentista.getNome());
					pagamento.setNome_paciente(paciente.getNome());
					pagamento.setDesc_consulta(consulta.getDescricaoConsulta());
					pagamento
							.setPagamento(consulta.getPagamento().getSigla() == 'P' ? EPagamento.PAGO
									: consulta.getPagamento().getSigla() == 'D' ? EPagamento.DEVE
											: EPagamento.ABERTO);
					int response = JOptionPane
							.showConfirmDialog(
									null,
									"PAGAMENTO"
											+ pagamento
											+ "\n'SIM' para pagar;\nAperte 'N�o' se n�o pagou!",
									"****** ATEN��O ******",
									JOptionPane.YES_OPTION,
									JOptionPane.WARNING_MESSAGE);
					if (response == 0) {
						consultaDao.alteraPagamento(pagamento.getId_consulta(),
								EPagamento.PAGO.getSigla());
						JOptionPane.showMessageDialog(null, "CONSULTA PAGA!!!");
						break;
					}
					consultaDao.alteraPagamento(pagamento.getId_consulta(),
							EPagamento.DEVE.getSigla());
					JOptionPane.showMessageDialog(null, "CONSULTA N�O PAGA!!!");
					break;
				}
			}

			JOptionPane.showMessageDialog(null, "PAGAMENTO CANCELADO!!!");
			break;
		}

	}
	
}
