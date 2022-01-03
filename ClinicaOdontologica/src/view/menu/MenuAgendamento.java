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
							"SELECIONE A OPÇÃO DESEJADA!"
									+ "\n\nCADASTRAR\nNo Botão de \"CADASTRAR\" é possivel fazer o cadastramento de novos agendamentos.\n"
									+ "Cada iten que abrir tem que ser preenchido de forma correta, pois não irá cadastrar.\n"
									+ "Caso seja deixado algum campo em branco, o cadastramento será cancelado!"
									+ "\n\nEDITAR\nNo Botão de \"EDITAR\" é possivel fazer a alteração das informações do agendamento.\n"
									+ "Cada iten que abrir tem que ser preenchido de forma correta, pois não irá realizar \nas alterações.\n"
									+ "Caso seja deixado algum campo em branco, a alterações irá continuar com as \ninformações anteriores"
									+ "\n\nEXCLUIR\nNo Botão de \"EXCLUIR\" é possivel excluir um agendamento. Para excluir é necessário\nter "
									+ "o código do serviço, para consegui-lo clique no botão \"CONSULTAR\"!"
									+ "\n\nCONSULTAR\nNo Botão de \"CONSULTAR\" é possivel consultar todos os agendamentos cadastrados.\n\n\n",
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
			aux = JOptionPane.showInputDialog("CÓDIGO DO DENTISTA: ");
			if (aux != null && (!aux.equals(""))) {
				agendamento.setId_dentista(Integer.parseInt(aux));
				agendamento.setDt_agendamento(cadData());
				agendamento.setHr_agendamento(cadHora(agendamento
						.getDt_agendamento()));
				aux = JOptionPane.showInputDialog("CÓDIGO DO FUNIONÁRIO: ");
				if (aux != null && (!aux.equals(""))) {
					agendamento.setId_funcionario(Integer.parseInt(aux));
					aux = JOptionPane.showInputDialog("CÓDIGO DO PACIENTE: ");
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
		aux = JOptionPane.showInputDialog("Data do Agendamento( Mês)");
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
		aux = JOptionPane.showInputDialog("Horário do Agendamento(Hora)");
		hora = ((aux != null && !aux.equals("")) ? Integer.parseInt(aux) : 00);
		aux = JOptionPane.showInputDialog("Horário do Agendamento(Minuto)");
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
			aux = JOptionPane.showInputDialog("CÓDIGO DO AGENDAMENTO: ");
			if (aux != null && (!aux.equals(""))) {
				agendamentoNovo.setId_agendamento(Integer.parseInt(aux));
				AgendamentoDao agendamentoDao = new AgendamentoDao();
				agendamentoAntigo = agendamentoDao.consulta(agendamentoNovo
						.getId_agendamento());
				aux = JOptionPane.showInputDialog("CÓDIGO ATUAL DO DENTISTA: "
						+ agendamentoAntigo.getId_dentista()
						+ "\nNOVO CÓDIGO DO DENTISTA: ");
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
							.showInputDialog("CÓDIGO ATUAL DO FUNCIONÁRIO: "
									+ agendamentoAntigo.getId_funcionario()
									+ "\nNOVO CÓDIGO DO FUNCIONÁRIO: ");
					if (aux != null) {
						agendamentoNovo
								.setId_funcionario((aux.equals("")) ? agendamentoAntigo
										.getId_funcionario() : Integer
										.parseInt(aux));
						aux = JOptionPane
								.showInputDialog("CÓDIGO ATUAL DO PACIENTE: "
										+ agendamentoAntigo.getId_paciente()
										+ "\nNOVO CÓDIGO DO PACIENTE: ");
						if (aux != null) {
							agendamentoNovo
									.setId_paciente((aux.equals("")) ? agendamentoAntigo
											.getId_paciente() : Integer
											.parseInt(aux));
							agendamentoNovo.setConsultaRealizada(agendamentoAntigo.getConsultaRealizada());
							int resposta = JOptionPane.showConfirmDialog(null,
									"Você deseja realmente alterar o Agendamento "
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
			JOptionPane.showMessageDialog(null, "ALTERAÇÂO CANCELADA!!!");
			break;
		}

	}

	public Date altData(Date dataAnterior) {
		int dia, mes, ano;
		Calendar data = Calendar.getInstance();
		Calendar calendarioNovo = Calendar.getInstance();
		calendarioNovo.setTime(dataAnterior);
		aux = JOptionPane
				.showInputDialog("A DATA DE AGENDAMENTO ATUAL É(DIA): "
						+ calendarioNovo.get(Calendar.DAY_OF_MONTH)
						+ "\nNOVA DATA DE AGENDAMENTO(DIA): ");
		dia = ((aux != null && !aux.equals("")) ? Integer.parseInt(aux)
				: calendarioNovo.get(Calendar.DAY_OF_MONTH));
		aux = JOptionPane
				.showInputDialog("A DATA DE AGENDAMENTO ATUAL É(MÊS): "
						+ (calendarioNovo.get(Calendar.MONTH) + 1)
						+ "\nNOVA DATA DE AGENDAMENTO(MÊS): ");
		mes = ((aux != null && !aux.equals("")) ? (Integer.parseInt(aux) - 1)
				: calendarioNovo.get(Calendar.MONTH));
		aux = JOptionPane
				.showInputDialog("A DATA DE AGENDAMENTO ATUAL É(ANO): "
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
				.showInputDialog("HORÁRIO ATUAL DO AGENDAMENTO(HORA): "
						+ hour.get(Calendar.HOUR_OF_DAY)
						+ "\nNOVO HORÁRIO DO AGENDAMENTO(HORA): ");
		hora = ((aux != null && !aux.equals("")) ? Integer.parseInt(aux) : hour
				.get(Calendar.HOUR_OF_DAY));
		aux = JOptionPane
				.showInputDialog("HORÁRIO ATUAL DO AGENDAMENTO(MINUTO): "
						+ hour.get(Calendar.MINUTE)
						+ "\nNOVO HORÁRIO DO AGENDAMENTO(MINUTO): ");
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
			aux = JOptionPane.showInputDialog("CÓDIGO DO DENTISTA: ");
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
								"Você deseja realmente excluir o "
										+ agendamento + " ?",
								"****** ATENÇÃO ******",
								JOptionPane.YES_OPTION,
								JOptionPane.WARNING_MESSAGE);
				if (resposta == 0) {
					agendamentoDao.exclui(agendamento.getId_agendamento());
					JOptionPane.showMessageDialog(null,
							"AGENDAMENTO EXCLUÍDO COM SUCESSO!!!");
					break;
				}
			}
			JOptionPane.showMessageDialog(null,
					"AGENDAMENTO NÃO FOI EXCLUÍDO!!!");
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
