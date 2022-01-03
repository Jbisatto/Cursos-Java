package view.menu;

import javax.swing.JOptionPane;

import bancoDeDados.BancoDadosException;

public class MenuAdministrador {
	public void String() throws BancoDadosException {
		int response = 0;

		do {

			String[] menupri = { "DENTISTA", "FUNCION�RIO", "SERVI�O", "SAIR" };
			response = JOptionPane
					.showOptionDialog(
							null // Center in window.
							,
							"SELECIONE A OP��O ABAIXO!\n\nDENTISTA\n\nNo Dentista vc tem as op��o de cadastrar novos dentistas,"
									+ "\n editar dentistas j� cadastrados, excluir dentistas e\n consultar dentistas\n\nFUNCION�RIO\n\n"
									+ "No Funcion�rio vc tem as op��o de cadastrar novos funcion�rios,"
									+ "\n editar funcion�rios j� cadastrados, excluir dentistas e\n consultar dentistas\n\n\n"
									+ "SERVI�O\n\nNo Servi�o vc tem as op��o de cadatrar novos servi�os,"
									+ "\n editar servi�os j� cadastrados, excluir servi�os e \n consultar servi�os\n\n\n" // Message
							,
							"****************************** ADMINISTRADOR ******************************" // Title
							, JOptionPane.YES_NO_OPTION // Option type
							, JOptionPane.PLAIN_MESSAGE // messageType
							, null // Icon (none)
							, menupri // Button text as above.
							, "None of your business" // Default button's label
					);
			switch (response) {
			case 0:
				MenuDentista menuDentista = new MenuDentista();
				menuDentista.String();

				break;
			case 1:
				MenuFuncionario menuFuncionario = new MenuFuncionario();
				menuFuncionario.String();

				break;
			case 2:
				MenuServico menuServico = new MenuServico();
				menuServico.String();

			default:
				break;
			}
		} while (response != -1 && response != 3);
	}
}
