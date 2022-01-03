package view.menu;

import javax.swing.JOptionPane;

import bancoDeDados.BancoDadosException;

public class MenuDentistaPri {
	public void String() throws BancoDadosException {
		int response = 0;

		do {

			String[] menupri = { "CONSULTA", "ITENS DE SERVIÇO","SAIR" };
			response = JOptionPane
					.showOptionDialog(
							null // Center in window.
							,
							"SELECIONE A OPÇÃO ABAIXO!\n\nCONSULTA\n\nNa Consulta vc tem as opção de cadastrar novas consultas,"
									+ "\n editar consultas já cadastrados, excluir consultas e\n consultar consultas\n\nITENS DO SERVIÇO\n\n"
									+ "No Itens do Serviço vc tem as opção de cadastrar novos Itens do Serviço,"
									+ "\n editar Itens do Serviço já cadastrados, excluir Itens do Serviço e\n consultar Itens do Serviço\n\n\n",
							"****************************** DENTISTA ******************************" // Title
							, JOptionPane.YES_NO_OPTION // Option type
							, JOptionPane.PLAIN_MESSAGE // messageType
							, null // Icon (none)
							, menupri // Button text as above.
							, "None of your business" // Default button's label
					);
			switch (response) {
			case 0:
				MenuConsulta menuConsulta = new MenuConsulta();
				menuConsulta.String();

				break;
			case 1:
				MenuItenSer menuItenSer = new MenuItenSer();
				menuItenSer.String();

			default:
				break;
			}
		} while (response != -1 && response != 2);
	}
}
