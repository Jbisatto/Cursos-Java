package view.menu;

import javax.swing.JOptionPane;

import bancoDeDados.BancoDadosException;

public class MenuAdministrador {
	public void String() throws BancoDadosException {
		int response = 0;

		do {

			String[] menupri = { "DENTISTA", "FUNCIONÁRIO", "SERVIÇO", "SAIR" };
			response = JOptionPane
					.showOptionDialog(
							null // Center in window.
							,
							"SELECIONE A OPÇÃO ABAIXO!\n\nDENTISTA\n\nNo Dentista vc tem as opção de cadastrar novos dentistas,"
									+ "\n editar dentistas já cadastrados, excluir dentistas e\n consultar dentistas\n\nFUNCIONÁRIO\n\n"
									+ "No Funcionário vc tem as opção de cadastrar novos funcionários,"
									+ "\n editar funcionários já cadastrados, excluir dentistas e\n consultar dentistas\n\n\n"
									+ "SERVIÇO\n\nNo Serviço vc tem as opção de cadatrar novos serviços,"
									+ "\n editar serviços já cadastrados, excluir serviços e \n consultar serviços\n\n\n" // Message
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
