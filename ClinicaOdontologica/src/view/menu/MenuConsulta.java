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
import dao.ConsultaDao;
import enumerador.EConsulta;
import enumerador.EPagamento;
import bancoDeDados.BancoDadosException;
import beans.Consulta;

public class MenuConsulta {
	String aux;

	public void String() throws HeadlessException, BancoDadosException {
		int response = 0;

		do {

			String[] menupri = { "CADASTRAR", "EXCLUIR", "EDITAR", "CONSULTAR",
					"SAIR" };
			response = JOptionPane
					.showOptionDialog(
							null // Center in window.
							,
							"SELECIONE A OP��O DESEJADA!"
									+ "\n\nCADASTRAR\nNo Bot�o de \"CADASTRAR\" � possivel fazer o cadastramento de novas consultas.\n"
									+ "Cada iten que abrir tem que ser preenchido de forma correta, pois n�o ir� cadastrar.\n"
									+ "Caso seja deixado algum campo em branco, o cadastramento ser� cancelado!"
									+ "\n\nEDITAR\nNo Bot�o de \"EDITAR\" � possivel fazer a altera��o das informa��es da consulta.\n"
									+ "Cada iten que abrir tem que ser preenchido de forma correta, pois n�o ir� realizar \nas altera��es.\n"
									+ "Caso seja deixado algum campo em branco, a altera��es ir� continuar com as \ninforma��es anteriores"
									+ "\n\nEXCLUIR\nNo Bot�o de \"EXCLUIR\" � possivel excluir uma consulta. Para excluir � necess�rio\nter "
									+ "o c�digo do servi�o, para consegui-lo clique no bot�o \"CONSULTAR\"!"
									+ "\n\nCONSULTAR\nNo Bot�o de \"CONSULTAR\" � possivel consultar todas as consultas cadastrados.\n\n\n",
							"****************************** CONSULTA ******************************" // Title
							, JOptionPane.YES_NO_OPTION // Option type
							, JOptionPane.PLAIN_MESSAGE // messageType
							, null // Icon (none)
							, menupri // Button text as above.
							, "None of your business" // Default button's label
					);
			switch (response) {
			case 0:
				cadConsulta();

				break;
			case 1:
				excluirConsulta();
				break;

			case 2:
				alterarAgendamento();
				break;

			case 3:
				consultaConsulta(0, ""," OK PRA BUSCAR / CANCELAR PARA SAIR");
				break;

			default:
				break;
			}
		} while (response != -1 && response != 4);
	}

	// ************************************************************
	// *****************CADASTRAR CONSULTA*************************
	// ************************************************************
	public void cadConsulta() throws HeadlessException, BancoDadosException {
		Consulta consulta = new Consulta();
		while (true) {
			aux = JOptionPane.showInputDialog("DIGITE O C�DIGO DO DENTISTA: ");
			if (aux != null && (!aux.equals(""))) {
				MenuAgendamento menuAgendamento = new MenuAgendamento();
				aux = menuAgendamento.consultaAgendamento(aux,1,"DIGITE O C�DIGO DO AGENDAMENTO");
				if (aux != null && (!aux.equals(""))) {
					consulta.setId_consulta(Integer.parseInt(aux));
					consulta.setId_agendamento(Integer.parseInt(aux));
					aux = JOptionPane
							.showInputDialog("DESCRI��O DA CONSULTA: ");
					if (aux != null && (!aux.equals(""))) {
						consulta.setDescricaoConsulta(aux);
						consulta.setPagamento(EPagamento.ABERTO);
						int resposta = JOptionPane.showConfirmDialog(null,
								consulta, "************ CONSULTA ************",
								JOptionPane.YES_OPTION,
								JOptionPane.QUESTION_MESSAGE);
						if (resposta == 0) {
							ConsultaDao consultaDao = new ConsultaDao();
							if (consultaDao.insere(consulta)) {
								AgendamentoDao agendamentoDao = new AgendamentoDao();
								agendamentoDao.alteraStatus(consulta.getId_agendamento(), EConsulta.CONSUTADO_SIM.getSigla());
								JOptionPane
										.showMessageDialog(null,
												"CADASTRAMENTO DE CONSULTA COM SUCESSO!!!");
								break;
							}
						}
					}
				}
			}
			JOptionPane.showMessageDialog(null,
					"CADASTRAMENTO DE CONSULTA CANCELADA!!!");
			break;
		}

	}

	// ************************************************************
	// *****************ALTERAR CONSULTA *************************
	public void alterarAgendamento() throws HeadlessException,
			BancoDadosException {
		Consulta consultaAntigo = new Consulta();
		Consulta consultaNovo = new Consulta();
		while (true) {
			aux = JOptionPane.showInputDialog("DIGITE O C�DIGO DO DENTISTA: ");
			if (aux != null && (!aux.equals(""))) {
				MenuAgendamento menuAgendamento = new MenuAgendamento();
				aux = menuAgendamento.consultaAgendamento(aux,1,"DIGITE O C�DIGO DA CONSULTA");
				consultaNovo.setId_consulta(Integer.parseInt(aux));
				ConsultaDao consultaDao = new ConsultaDao();
				consultaAntigo = consultaDao.consulta(consultaNovo
						.getId_consulta());
				consultaNovo.setId_agendamento(consultaAntigo.getId_agendamento());
				aux = JOptionPane
						.showInputDialog("DESCRI��O ATUAL DA CONSULTA: "
								+ consultaAntigo.getDescricaoConsulta()
								+ "\nNOVA DESCRI��O ATUAL DA CONSULTA: ");
				if (aux != null) {
					consultaNovo
							.setDescricaoConsulta((aux.equals("")) ? consultaAntigo
									.getDescricaoConsulta() : (aux));
					consultaNovo.setPagamento(consultaAntigo.getPagamento());
					int resposta = JOptionPane.showConfirmDialog(null,
							"Voc� deseja realmente alterar o Consulta "
									+ consultaAntigo + " para :\n"
									+ consultaNovo + " ?",
							"************ AGENDAMENTO ************",
							JOptionPane.YES_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (resposta == 0) {
						if (consultaDao.altera(consultaNovo)) {
							JOptionPane.showMessageDialog(null,
									"CONSULTA ALTERADA COM SUCESSO!!!");
							break;
						}
					}
				}
			}
			JOptionPane.showMessageDialog(null, "ALTERA��O CANCELADA!!!");
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

	// *************************************************
	// ***************EXCLUIR CONSULTA******************
	// *************************************************
	public void excluirConsulta() throws BancoDadosException {
		Consulta consulta = new Consulta();
		while (true) {
			aux = JOptionPane.showInputDialog("C�DIGO DO CONSULTA: ");
			if (aux != null && (!aux.equals(""))) {
				consulta.setId_consulta(Integer.parseInt(aux));
				ConsultaDao consultaDao = new ConsultaDao();
				consulta = consultaDao.consulta(consulta.getId_consulta());
				int resposta = JOptionPane.showConfirmDialog(null,
						"Voc� deseja realmente excluir o " + consulta + " ?",
						"****** ATEN��O ******", JOptionPane.YES_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (resposta == 0) {
					consultaDao.exclui(consulta.getId_consulta());
					JOptionPane.showMessageDialog(null,
							"CONSULTA EXCU�DA COM SUCESSO!!!");
					break;
				}
			}
			JOptionPane.showMessageDialog(null, "CONSULTA N�O FOI EXCLU�DO!!!");
			break;
		}
	}

	// ***************************************************
	// ***************CONSULTAR CONSULTA******************
	public int consultaConsulta(int codigo, String nome,String frase)
			throws BancoDadosException {
		StringBuffer lista = new StringBuffer("");
		aux = "";
		do {
			lista = codigo != 0 ? listaConsultaAgendamento(nome) : aux
					.equals("") ? listaConsulta() : listaConsulta(aux);
			String literal = lista.toString();
			JTextArea area = new JTextArea(literal);
			JScrollPane scroll = new JScrollPane(area);
			scroll.setPreferredSize(new Dimension(400, 300));
			// JOptionPane.showMessageDialog(null, scroll);
			aux = JOptionPane.showInputDialog(null, scroll,
					frase, JOptionPane.YES_NO_CANCEL_OPTION);
		} while (aux != null && codigo == 0);
		return aux == null ? 0 : aux.equals("") ? 0 : Integer.parseInt(aux);
	}

	public StringBuffer listaConsulta() throws BancoDadosException {
		List<Consulta> consultas = new ArrayList<Consulta>();
		StringBuffer lista = new StringBuffer("");
		ConsultaDao consultaDao = new ConsultaDao();
		consultas = consultaDao.consulta();

		for (Consulta consulta : consultas) {
			lista.append(consulta);
		}
		return lista;
	}

	public StringBuffer listaConsulta(String aux) throws BancoDadosException {
		List<Consulta> consultas = new ArrayList<Consulta>();
		StringBuffer lista = new StringBuffer("");
		ConsultaDao consultaDao = new ConsultaDao();
		consultas = consultaDao.consulta(aux);

		for (Consulta consulta : consultas) {
			lista.append(consulta);
		}
		return lista;
	}

	public StringBuffer listaConsultaAgendamento(String aux)
			throws BancoDadosException {
		List<Consulta> consultas = new ArrayList<Consulta>();
		StringBuffer lista = new StringBuffer("");
		ConsultaDao consultaDao = new ConsultaDao();
		consultas = consultaDao.consulta(aux);

		for (Consulta consulta : consultas) {
			lista.append(consulta);
		}
		return lista;
	}
}