package view.menu;

import javax.swing.JOptionPane;

import bancoDeDados.BancoDadosException;

public class MenuDentistaPri {
	public void String() throws BancoDadosException {
		int response = 0;

		do {

			String[] menupri = { "CONSULTA", "ITENS DE SERVI�O","SAIR" };
			response = JOptionPane
					.showOptionDialog(
							null // Center in window.
							,
							"SELECIONE A OP��O ABAIXO!\n\nCONSULTA\n\nNa Consulta vc tem as op��o de cadastrar novas consultas,"
									+ "\n editar consultas j� cadastrados, excluir consultas e\n consultar consultas\n\nITENS DO SERVI�O\n\n"
									+ "No Itens do Servi�o vc tem as op��o de cadastrar novos Itens do Servi�o,"
									+ "\n editar Itens do Servi�o j� cadastrados, excluir Itens do Servi�o e\n consultar Itens do Servi�o\n\n\n",
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
