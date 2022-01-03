package view.menu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JOptionPane;

import bancoDeDados.BancoDadosException;
import bancoDeDados.BancoDeDados;

public class MenuPrincipal {
	String aux;

	public static void main(String[] args) throws IOException,
			BancoDadosException, ParseException, SQLException {

		int response = 0;
		String aux;
		MenuPrincipal menuprincipal = new MenuPrincipal();
		menuprincipal.configuracao();
		do {
			
			String[] menupri = { "ADMINISTRADOR", "SECRET�RIA", "DENTISTA",
					"SAIR" };
			response = JOptionPane
					.showOptionDialog(
							null // Center in window.
							,
							"SELECIONE A SUA ARE�!"
									+ "\n\nADMINISTRADOR\nAdministrador poder� realizar fun��es relacionado a Dentista,"
									+ " Funcion�rios e Serv�os\n\nSECRET�RIA\n"
									+ "Secret�ria poder� realizar fun��es relacionado a Paciente e Agendamento\n\n"
									+ "DENTISTA\nDentista poder� realizar fun��es relacionado a Consulta e Itens de Servi�os\n\n\n"
									+ "CONFIGURA��O\nConfigura��o poder� alterar as configura��es do Banco de Dados\n\n\n" // Message
							,
							"************************************** SISTEMA ODONTOL�GICO **************************************" // Title
							, JOptionPane.YES_NO_OPTION // Option type
							, JOptionPane.PLAIN_MESSAGE // messageType
							, null // Icon (none)
							, menupri // Button text as above.
							, "None of your business" // Default button's label
					);
			switch (response) {
			case 0:
				aux = JOptionPane.showInputDialog(null,
						"Digite a senha do Administrador:");
				if (aux != null && (!aux.equals("")) && aux.equals(aux)) {
					MenuAdministrador menuAdministrador = new MenuAdministrador();
					menuAdministrador.String();
				} else {
					JOptionPane.showMessageDialog(null, "Senha errada!!!");
				}

				break;
			case 1:
				MenuSecretaria menuSecretaria = new MenuSecretaria();
				menuSecretaria.String();

				break;
			case 2:
				MenuDentistaPri menuDentistaPri = new MenuDentistaPri();
				menuDentistaPri.String();

				break;
			default:
				break;
			}

		} while (response != -1 && response != 3);

	}

	public void configuracao() throws BancoDadosException, SQLException{
		BancoDeDados banco = new BancoDeDados();
		Connection conexao = banco.conectar();
		while (true) {
		if(banco.estaConectado()){
			banco.desconectar();
			System.out.println("esta conectado");
			break;
		}
		JOptionPane.showMessageDialog(null, "Banco n�o Conectado!");
		int resposta = JOptionPane.showConfirmDialog(null,
				"VERIFIQUE AS CONFIGURA��ES DO BANCO?", "******CONFIGURA��O******",
				JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
		
			if (resposta == 0) {

				aux = JOptionPane.showInputDialog("\"Servidor\" atual : "
						+ banco.getServidor() + "\nDIGITE O NOVO NOME:");
				if (aux != null) {
					banco.setServidor((!aux.equals("")) ? aux : banco
							.getServidor());
					aux = JOptionPane
							.showInputDialog("\"Server Name\" atual : "
									+ banco.getServerName()
									+ "\nDIGITE O NOVO NOME:");
					if (aux != null) {
						banco.setServerName((!aux.equals("")) ? aux : banco
								.getServerName());
						aux = JOptionPane
								.showInputDialog("\"Mydatabase\" atual : "
										+ banco.getMydatabase()
										+ "\nDIGITE O NOVO NOME:");
						if (aux != null) {
							banco.setMydatabase((!aux.equals("")) ? aux : banco
									.getMydatabase());
							aux = JOptionPane
									.showInputDialog("\"User\" atual : "
											+ banco.getUser()
											+ "\nDIGITE O NOVO NOME:");
							if (aux != null) {
								banco.setUser((!aux.equals("")) ? aux : banco
										.getUser());
								aux = JOptionPane
										.showInputDialog("\"Key\" atual : "
												+ banco.getKey()
												+ "\nDIGITE O NOVO NOME:");
								if (aux != null) {
									banco.setKey((!aux.equals("")) ? aux
											: banco.getKey());
									JOptionPane.showMessageDialog(null,
											"Banco configurado!\n"
											+ "ATEN��O: ENTRAR EM CONTATO COM O SUPORTE\n"
											+ "PARA ALTERA��O DE SENHA PADR�O!!!");
									try {
										conexao = banco.conectar();
										JOptionPane.showMessageDialog(null, "Conectado no Banco!");
										banco.desconectar();
										banco.gerarTabelas();
										banco.inserir();
									} catch (BancoDadosException e) {
										JOptionPane.showMessageDialog(null, "Banco n�o Conectado!");
									}
									break;
									// System.out.println(banco.getServidor()
									// + "\n" + banco.getServerName()
									// + "\n" + banco.getMydatabase()
									// + "\n" + banco.getUser() + "\n"
									// + banco.getKey());
								}
							}
						}
					}

				}
			
			JOptionPane.showMessageDialog(null, "Banco configurado Padr�o!");
			break;
		}
		
	}
}
}
