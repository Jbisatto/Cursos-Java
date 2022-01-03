package view.menu;

import java.text.ParseException;

import javax.swing.JOptionPane;

import bancoDeDados.BancoDadosException;


public class MenuSecretaria {
	public void String() throws BancoDadosException, ParseException {
		int response = 0;

		do {

			String[] menupri = { "PACIENTE", "AGENDAMENTO", "SAIR" };
			response = JOptionPane
					.showOptionDialog(
							null // Center in window.
							,
							"SELECIONE ENTRE PACIENTE OU DENTISTA!\n\nPACIENTE\n\nNo Paciente vc tem as opção de cadastrar novos pacientes,"
									+ "\n editar paciente já cadastrados, excluir pacientes e\n consultar pacientes\n\n\n"
									+ "AGENDAMENTO\n\n No Agendamento vc tem as opção de cadastrar novos agendamento,\n"
									+ "editar agendamento já cadastrados, excluir agendamento e\n consultar agendamento\n\n\n" // Message
							,
							"****************************** SECRETÁRIA ******************************" // Title
							, JOptionPane.YES_NO_OPTION // Option type
							, JOptionPane.PLAIN_MESSAGE // messageType
							, null // Icon (none)
							, menupri // Button text as above.
							, "None of your business" // Default button's label
					);
			switch (response) {
			case 0:
				MenuPaciente menuPaciente = new MenuPaciente();
				menuPaciente.String();

				break;
			case 1:
				MenuAgendamento menuAgendamento = new MenuAgendamento();
				menuAgendamento.String();

			default:
				break;
			}
		} while (response != -1 && response != 2);
	}
}
