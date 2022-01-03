package view.menu;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.AgendamentoDao;
import enumerador.EConsulta;
import bancoDeDados.BancoDadosException;
import beans.Agendamento;

public class MenuAgendamento {
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
							"SELECIONE A OP��O DESEJADA!"
									+ "\n\nCADASTRAR\nNo Bot�o de \"CADASTRAR\" � possivel fazer o cadastramento de novos agendamentos.\n"
									+ "Cada iten que abrir tem que ser preenchido de forma correta, pois n�o ir� cadastrar.\n"
									+ "Caso seja deixado algum campo em branco, o cadastramento ser� cancelado!"
									+ "\n\nEDITAR\nNo Bot�o de \"EDITAR\" � possivel fazer a altera��o das informa��es do agendamento.\n"
									+ "Cada iten que abrir tem que ser preenchido de forma correta, pois n�o ir� realizar \nas altera��es.\n"
									+ "Caso seja deixado algum campo em branco, a altera��es ir� continuar com as \ninforma��es anteriores"
									+ "\n\nEXCLUIR\nNo Bot�o de \"EXCLUIR\" � possivel excluir um agendamento. Para excluir � necess�rio\nter "
									+ "o c�digo do servi�o, para consegui-lo clique no bot�o \"CONSULTAR\"!"
									+ "\n\nCONSULTAR\nNo Bot�o de \"CONSULTAR\" � possivel consultar todos os agendamentos cadastrados.\n\n\n",
							"****************************** AGENDAMENTO ******************************" // Title
							, JOptionPane.YES_NO_OPTION // Option type
							, JOptionPane.PLAIN_MESSAGE // messageType
							, null // Icon (none)
							, menupri // Button text as above.
							, "None of your business" // Default button's label
					);
			switch (response) {
			case 0:
				cadAgendamento();
				break;
			case 1:
				alterarAgendamento();
				break;
			case 2:
				excluirAgendamento();
				break;
			case 3:
				consultaAgendamento("",0,"CANCELAR PARA SAIR");
				break;
			default:
				break;
			}
		} while (response != -1 && response != 4);
	}

	// ************************************************************
	// *****************CADASTRAR AGENDAMENTO********************
	// ************************************************************
	public void cadAgendamento() throws HeadlessException, BancoDadosException {
		Agendamento agendamento = new Agendamento();
		while (true) {
			aux = JOptionPane.showInputDialog("C�DIGO DO DENTISTA: ");
			if (aux != null && (!aux.equals(""))) {
				agendamento.setId_dentista(Integer.parseInt(aux));
				agendamento.setDt_agendamento(cadData());
				agendamento.setHr_agendamento(cadHora(agendamento
						.getDt_agendamento()));
				aux = JOptionPane.showInputDialog("C�DIGO DO FUNION�RIO: ");
				if (aux != null && (!aux.equals(""))) {
					agendamento.setId_funcionario(Integer.parseInt(aux));
					aux = JOptionPane.showInputDialog("C�DIGO DO PACIENTE: ");
					if (aux != null && (!aux.equals(""))) {
						agendamento.setId_paciente(Integer.parseInt(aux));
						agendamento.setConsultaRealizada(EConsulta.CONSULTADO_NAO);
						int resposta = JOptionPane.showConfirmDialog(null,
								agendamento,
								"************ AGENDAMENTO ************",
								JOptionPane.YES_OPTION,
								JOptionPane.QUESTION_MESSAGE);
						if (resposta == 0) {
							AgendamentoDao agendamentoDao = new AgendamentoDao();
							if (agendamentoDao.insere(agendamento)) {
								JOptionPane.showMessageDialog(null,
										"AGENDAMENTO COM SUCESSO!!!");
								break;
							}
						}
					}
				}
			}
			JOptionPane.showMessageDialog(null, "AGENDAMENTO CANCELADO!!!");
			break;
		}

	}

	public Date cadData() {
		int dia, mes, ano;
		Calendar data = Calendar.getInstance();
		aux = JOptionPane.showInputDialog("Data do Agendamento( Dia)");
		dia = ((aux != null && !aux.equals("")) ? Integer.parseInt(aux) : 00);
		aux = JOptionPane.showInputDialog("Data do Agendamento( M�s)");
		mes = ((aux != null && !aux.equals("")) ? (Integer.parseInt(aux) - 1)
				: 00);
		aux = JOptionPane.showInputDialog("Data do Agendamento( Ano)");
		ano = ((aux != null && !aux.equals("")) ? Integer.parseInt(aux) : 0000);
		data.set(ano, mes, dia);
		return data.getTime();
	}

	public Date cadHora(Date dati) {
		int hora, minuto;
		Calendar data = Calendar.getInstance();
		Calendar data2 = Calendar.getInstance();
		data2.setTime(dati);
		aux = JOptionPane.showInputDialog("Hor�rio do Agendamento(Hora)");
		hora = ((aux != null && !aux.equals("")) ? Integer.parseInt(aux) : 00);
		aux = JOptionPane.showInputDialog("Hor�rio do Agendamento(Minuto)");
		minuto = ((aux != null && !aux.equals("")) ? Integer.parseInt(aux) : 00);

		data.set(data2.get(Calendar.YEAR), data2.get(Calendar.MONTH),
				data2.get(Calendar.DAY_OF_MONTH), hora, minuto, 00);

		return data.getTime();
	}

	// ************************************************************
	// *****************ALTERAR AGENDAMENTO********************
	// ************************************************************
	public void alterarAgendamento() throws HeadlessException,
			BancoDadosException {
		Agendamento agendamentoAntigo = new Agendamento();
		Agendamento agendamentoNovo = new Agendamento();
		while (true) {
			aux = JOptionPane.showInputDialog("C�DIGO DO AGENDAMENTO: ");
			if (aux != null && (!aux.equals(""))) {
				agendamentoNovo.setId_agendamento(Integer.parseInt(aux));
				AgendamentoDao agendamentoDao = new AgendamentoDao();
				agendamentoAntigo = agendamentoDao.consulta(agendamentoNovo
						.getId_agendamento());
				aux = JOptionPane.showInputDialog("C�DIGO ATUAL DO DENTISTA: "
						+ agendamentoAntigo.getId_dentista()
						+ "\nNOVO C�DIGO DO DENTISTA: ");
				if (aux != null) {
					agendamentoNovo
							.setId_dentista((aux.equals("")) ? agendamentoAntigo
									.getId_dentista() : Integer.parseInt(aux));
					agendamentoNovo.setDt_agendamento(altData(agendamentoAntigo
							.getDt_agendamento()));
					agendamentoNovo.setHr_agendamento(altHora(
							agendamentoAntigo.getHr_agendamento(),
							agendamentoNovo.getDt_agendamento()));
					aux = JOptionPane
							.showInputDialog("C�DIGO ATUAL DO FUNCION�RIO: "
									+ agendamentoAntigo.getId_funcionario()
									+ "\nNOVO C�DIGO DO FUNCION�RIO: ");
					if (aux != null) {
						agendamentoNovo
								.setId_funcionario((aux.equals("")) ? agendamentoAntigo
										.getId_funcionario() : Integer
										.parseInt(aux));
						aux = JOptionPane
								.showInputDialog("C�DIGO ATUAL DO PACIENTE: "
										+ agendamentoAntigo.getId_paciente()
										+ "\nNOVO C�DIGO DO PACIENTE: ");
						if (aux != null) {
							agendamentoNovo
									.setId_paciente((aux.equals("")) ? agendamentoAntigo
											.getId_paciente() : Integer
											.parseInt(aux));
							agendamentoNovo.setConsultaRealizada(agendamentoAntigo.getConsultaRealizada());
							int resposta = JOptionPane.showConfirmDialog(null,
									"Voc� deseja realmente alterar o Agendamento "
											+ agendamentoAntigo + " para :\n"
											+ agendamentoNovo + " ?",
									"************ AGENDAMENTO ************",
									JOptionPane.YES_OPTION,
									JOptionPane.QUESTION_MESSAGE);
							if (resposta == 0) {
								if (agendamentoDao.altera(agendamentoNovo)) {
									JOptionPane
											.showMessageDialog(null,
													"AGENDAMENTO ALTERADO COM SUCESSO!!!");
									break;
								}
							}
						}
					}
				}
			}
			JOptionPane.showMessageDialog(null, "ALTERA��O CANCELADA!!!");
			break;
		}

	}

	public Date altData(Date dataAnterior) {
		int dia, mes, ano;
		Calendar data = Calendar.getInstance();
		Calendar calendarioNovo = Calendar.getInstance();
		calendarioNovo.setTime(dataAnterior);
		aux = JOptionPane
				.showInputDialog("A DATA DE AGENDAMENTO ATUAL �(DIA): "
						+ calendarioNovo.get(Calendar.DAY_OF_MONTH)
						+ "\nNOVA DATA DE AGENDAMENTO(DIA): ");
		dia = ((aux != null && !aux.equals("")) ? Integer.parseInt(aux)
				: calendarioNovo.get(Calendar.DAY_OF_MONTH));
		aux = JOptionPane
				.showInputDialog("A DATA DE AGENDAMENTO ATUAL �(M�S): "
						+ (calendarioNovo.get(Calendar.MONTH) + 1)
						+ "\nNOVA DATA DE AGENDAMENTO(M�S): ");
		mes = ((aux != null && !aux.equals("")) ? (Integer.parseInt(aux) - 1)
				: calendarioNovo.get(Calendar.MONTH));
		aux = JOptionPane
				.showInputDialog("A DATA DE AGENDAMENTO ATUAL �(ANO): "
						+ calendarioNovo.get(Calendar.YEAR)
						+ "\nNOVA DATA DE AGENDAMENTO(ANO): ");
		ano = ((aux != null && !aux.equals("")) ? Integer.parseInt(aux)
				: calendarioNovo.get(Calendar.YEAR));
		data.set(ano, mes, dia);
		return data.getTime();
	}

	public Date altHora(Date hora2, Date dati) {
		int hora, minuto;
		Calendar data = Calendar.getInstance();
		Calendar data2 = Calendar.getInstance();
		Calendar hour = Calendar.getInstance();
		data2.setTime(dati);
		hour.setTime(hora2);
		aux = JOptionPane
				.showInputDialog("HOR�RIO ATUAL DO AGENDAMENTO(HORA): "
						+ hour.get(Calendar.HOUR_OF_DAY)
						+ "\nNOVO HOR�RIO DO AGENDAMENTO(HORA): ");
		hora = ((aux != null && !aux.equals("")) ? Integer.parseInt(aux) : hour
				.get(Calendar.HOUR_OF_DAY));
		aux = JOptionPane
				.showInputDialog("HOR�RIO ATUAL DO AGENDAMENTO(MINUTO): "
						+ hour.get(Calendar.MINUTE)
						+ "\nNOVO HOR�RIO DO AGENDAMENTO(MINUTO): ");
		minuto = ((aux != null && !aux.equals("")) ? Integer.parseInt(aux)
				: hour.get(Calendar.MINUTE));

		data.set(data2.get(Calendar.YEAR), data2.get(Calendar.MONTH),
				data2.get(Calendar.DAY_OF_MONTH), hora, minuto, 00);

		return data.getTime();
	}

	// *************************************************
	// ***************EXCLUIR AGENDAMENTO******************
	// *************************************************
	public void excluirAgendamento() throws BancoDadosException {
		Agendamento agendamento = new Agendamento();
		while (true) {
			aux = JOptionPane.showInputDialog("C�DIGO DO DENTISTA: ");
			if (aux != null && (!aux.equals(""))) {
				agendamento.setId_dentista(Integer.parseInt(aux));
				agendamento.setDt_agendamento(cadData());
				agendamento.setHr_agendamento(cadHora(agendamento
						.getDt_agendamento()));
				AgendamentoDao agendamentoDao = new AgendamentoDao();
				agendamento = agendamentoDao.consulta(agendamento
						.getId_agendamento());
				int resposta = JOptionPane
						.showConfirmDialog(null,
								"Voc� deseja realmente excluir o "
										+ agendamento + " ?",
								"****** ATEN��O ******",
								JOptionPane.YES_OPTION,
								JOptionPane.WARNING_MESSAGE);
				if (resposta == 0) {
					agendamentoDao.exclui(agendamento.getId_agendamento());
					JOptionPane.showMessageDialog(null,
							"AGENDAMENTO EXCLU�DO COM SUCESSO!!!");
					break;
				}
			}
			JOptionPane.showMessageDialog(null,
					"AGENDAMENTO N�O FOI EXCLU�DO!!!");
			break;
		}
	}

	// ***************************************************
	// ***************CONSULTAR AGENDAMENTO******************
	// ***************************************************
	public String consultaAgendamento(String aux,int codigo,String frase) throws BancoDadosException {
		StringBuffer lista = new StringBuffer("");
		do {
			lista = aux.equals("") ? listaAgendamento() : listaAgendamento(Integer.parseInt(aux));
			String literal = lista.toString();
			JTextArea area = new JTextArea(literal);
			JScrollPane scroll = new JScrollPane(area);
			scroll.setPreferredSize(new Dimension(400, 300));
			aux = JOptionPane.showInputDialog(null, scroll,
					frase, JOptionPane.YES_NO_CANCEL_OPTION);
		} while (aux != null && codigo==0);
		return aux;
	}

	public StringBuffer listaAgendamento() throws BancoDadosException {
		List<Agendamento> agendamentos = new ArrayList<Agendamento>();
		StringBuffer lista = new StringBuffer("");
		AgendamentoDao agendamentoDao = new AgendamentoDao();
		agendamentos = agendamentoDao.consulta();

		for (Agendamento agendamento : agendamentos) {
			lista.append(agendamento);
		}
		return lista;
	}

	public StringBuffer listaAgendamento(int aux) throws BancoDadosException {
		List<Agendamento> agendamentos = new ArrayList<Agendamento>();
		StringBuffer lista = new StringBuffer("");
		AgendamentoDao agendamentoDao = new AgendamentoDao();
		agendamentos = agendamentoDao.consultaDentista(aux);

		for (Agendamento agendamento : agendamentos) {
			lista.append(agendamento);
		}
		return lista;
	}

}
