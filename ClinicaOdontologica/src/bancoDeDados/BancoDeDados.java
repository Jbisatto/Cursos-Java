package bancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import dao.AgendamentoDao;
import dao.ConsultaDao;
import dao.DentistaDao;
import dao.FuncionarioDao;
import dao.ItensServicoConsultaDAO;
import dao.PacienteDao;
import dao.ServicoDao;

public class BancoDeDados {
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet resultset = null;
	private static String serverName = "localhost:3306"; // caminho do servidor
															// do BD
	private static String mydatabase = "dbagenda?useTimezone=true&serverTimezone=UTC"; // nome do seu banco de dados
	private static String servidor = "jdbc:mysql://";
	private static String user = "root"; // nome de um usuário de seu BD
	private static String key = "bisatt"; // sua senha de acesso
	// private static String user = "root"; // nome de um usuário de seu BD
	// private static String key = "adm"; // sua senha de acesso
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String teste = "";

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getMydatabase() {
		return mydatabase;
	}

	public void setMydatabase(String mydatabase) {
		this.mydatabase = mydatabase;
	}

	public String getServidor() {
		return servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public Connection conectar() throws BancoDadosException {
		try {
			Class.forName(this.getDriver());
			connection = DriverManager.getConnection(this.getServidor()
					+ getServerName() + "/" + getMydatabase(), getUser(),
					getKey());
			return connection;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean estaConectado() {
		if (this.connection != null) {
			return true;
		} else {
			return false;
		}
	}

	public void desconectar() {
		try {
			this.connection.close();
			connection = null;
		} catch (Exception e) {
			System.out.println("Erro :" + e.getMessage());
		}
	}

	public void gerarTabelas() {
		try {
			FuncionarioDao fDao = new FuncionarioDao();
			PacienteDao pDao = new PacienteDao();
			DentistaDao dDao = new DentistaDao();
			ServicoDao sDao = new ServicoDao();
			AgendamentoDao aDao = new AgendamentoDao();
			ConsultaDao cDao = new ConsultaDao();
			ItensServicoConsultaDAO iscDao = new ItensServicoConsultaDAO();
			
//ANTES DE CRIAR SÃO FEITOS DELETE DE TABELAS SE HOUVER ALGUMA COM O MESMO NOME			
			iscDao.destroiTabela();
			cDao.destroiTabela();
			aDao.destroiTabela();
			sDao.destroiTabela();
			dDao.destroiTabela();
			pDao.destroiTabela();
			fDao.destroiTabela();
//CRIAÇÃO DE TABELAS			
			fDao.criaTabela();
			pDao.criaTabela();
			dDao.criaTabela();
			sDao.criaTabela();
			aDao.criaTabela();
			cDao.criaTabela();
			iscDao.criaTabela();
			
			
			JOptionPane.showMessageDialog(null, "Estrutura do Banco criada!");
		} catch (Exception e) {
			System.out.println("Erro :" + e.getMessage());

			JOptionPane.showMessageDialog(null,
					"Estrutura do Banco Não criada!");
		}

	}

	public boolean inserir() throws BancoDadosException, SQLException {
		BancoDeDados bd = new BancoDeDados();
		List<String> negrice = new ArrayList<String>();
		Connection conexao = bd.conectar();

		negrice.add("Insert into funcionario (desc_funcionario,tipo_funcionario,email,telefone,sexo,cpf) values (\"Joana\",\"Secretária\",\"joana@gmail.com\",\"33123123\",\"feminino\",\"999900000\");");
		negrice.add("Insert into funcionario (desc_funcionario,tipo_funcionario,email,telefone,sexo,cpf) values (\"Kelly\",\"Secretária\",\"kelly@gmail.com\",\"34445334\",\"feminino\",\"222222222\");");
		negrice.add("Insert into Paciente (desc_paciente,email,telefone,sexo,cpf,dt_nascimento,convenio) values"
				+ "(\"jefferson\",\"jefferson.bisatto@gamil.com\",\"99158513\",\"masculino\",\"01010970980\",'1988-02-20',\"unimed\");");
		negrice.add("Insert into Paciente (desc_paciente,email,telefone,sexo,cpf,dt_nascimento,convenio) values"
				+ "(\"Willian\",\"williandavis@gamil.com\",\"99158513\",\"masculino\",\"01010970980\",'1988-02-20',\"unimed\");");
		negrice.add("Insert into Paciente (desc_paciente,email,telefone,sexo,cpf,dt_nascimento,convenio) values"
				+ "(\"Dino\",\"dinossauro@gamil.com\",\"99158513\",\"masculino\",\"01010970980\",'1988-02-20',\"unimed\");");
		negrice.add("Insert into Paciente (desc_paciente,email,telefone,sexo,cpf,dt_nascimento,convenio) values"
				+ "(\"Jose Claudio\",\"joseclaudio@gamil.com\",\"99158513\",\"masculino\",\"01010970980\",'1988-02-20',\"unimed\");");
		negrice.add("Insert into Paciente (desc_paciente,email,telefone,sexo,cpf,dt_nascimento,convenio) values"
				+ "(\"Med\",\"medputasso@gamil.com\",\"99158513\",\"masculino\",\"01010970980\",'1988-02-20',\"unimed\");");

		negrice.add("Insert into Dentista (desc_dentista,email,telefone,cro,cpf,sexo) values"
				+ "(\"Dra. Ana Paula\",\"drapaulinha@gamil.com\",\"32323121\",\"1234567890\",\"01010970980\",\"Feminino\");");
		negrice.add("Insert into Dentista (desc_dentista,email,telefone,cro,cpf,sexo) values"
				+ "(\"Dr. Diego\",\"drdiego@gamil.com\",\"33211121\",\"755666788\",\"1111111111\",\"Masculino\");");
		negrice.add("Insert into Dentista (desc_dentista,email,telefone,cro,cpf,sexo) values"
				+ "(\"Dr. Paulo\",\"drpaulo@gamil.com\",\"32323121\",\"1234567890\",\"999999999\",\"Masculino\");");
		negrice.add("Insert into Dentista (desc_dentista,email,telefone,cro,cpf,sexo) values"
				+ "(\"Dr. Rodrigo\",\"drrodrigo@gamil.com\",\"32323121\",\"1234567890\",\"888888888\",\"Masculino\");");
		negrice.add("Insert into Dentista (desc_dentista,email,telefone,cro,cpf,sexo) values"
				+ "(\"Dra Daniela\",\"dra daniela@gamil.com\",\"23231123\",\"434344543\",\"777777777\",\"Feminino\");");

		negrice.add("insert into Agendamento (id_dentista,dt_agendamento,hr_agendamento,Funcionario_id_funcionario,Paciente_id_paciente,`consulta_realizada`) values"
				+ "(1,'2015-04-20','14:30:00',2,1,'S');");
		negrice.add("insert into Agendamento (id_dentista,dt_agendamento,hr_agendamento,Funcionario_id_funcionario,Paciente_id_paciente,`consulta_realizada`) values"
				+ "(2,'2015-04-20','14:30:00',1,2,'S');");
		negrice.add("insert into Agendamento (id_dentista,dt_agendamento,hr_agendamento,Funcionario_id_funcionario,Paciente_id_paciente,`consulta_realizada`) values"
				+ "(3,'2015-04-20','14:30:00',1,3,'S');");
		negrice.add("insert into Agendamento (id_dentista,dt_agendamento,hr_agendamento,Funcionario_id_funcionario,Paciente_id_paciente,`consulta_realizada`) values"
				+ "(4,'2015-04-20','14:30:00',2,4,'S');");
		negrice.add("insert into Agendamento (id_dentista,dt_agendamento,hr_agendamento,Funcionario_id_funcionario,Paciente_id_paciente,`consulta_realizada`) values"
				+ "(5,'2015-04-20','14:30:00',2,5,'S');");
		negrice.add("insert into Agendamento (id_dentista,dt_agendamento,hr_agendamento,Funcionario_id_funcionario,Paciente_id_paciente,`consulta_realizada`) values"
				+ "(1,'2015-04-20','15:30:00',1,1,'N');");
		negrice.add("insert into Agendamento (id_dentista,dt_agendamento,hr_agendamento,Funcionario_id_funcionario,Paciente_id_paciente,`consulta_realizada`) values"
				+ "(1,'2015-04-21','14:30:00',1,1,'N');");

		negrice.add("INSERT INTO SERVICO (desc_servico,preco) VALUES(\"Abturação\",'150.00');");
		negrice.add("INSERT INTO SERVICO (desc_servico,preco) VALUES(\"Limpeza\",'100.00');");
		negrice.add("INSERT INTO SERVICO (desc_servico,preco) VALUES(\"Remoção de dentes\",'50.00');");
		negrice.add("INSERT INTO SERVICO (desc_servico,preco) VALUES(\"Manutenção de aparelho\",'80.00');");
		negrice.add("INSERT INTO SERVICO (desc_servico,preco) VALUES(\"Tratamento de canal\",'110.00');");
		negrice.add("INSERT INTO SERVICO (desc_servico,preco) VALUES(\"Aparelho superior\",'300.00');");
		negrice.add("INSERT INTO SERVICO (desc_servico,preco) VALUES(\"Aparelho Inferior\",'250.00');");

		negrice.add("insert into consulta (id_consultas,id_agendamento,desc_consulta,pagamento) values"
				+ "(1,1,\"Abturação de dentes inferiores\",'D');");
		negrice.add("insert into consulta (id_consultas,id_agendamento,desc_consulta,pagamento) values"
				+ "(2,2,\"Limpeza de dentes inferiores\",'P');");
		negrice.add("insert into consulta (id_consultas,id_agendamento,desc_consulta,pagamento) values"
				+ "(3,3,\"Remoção de dentes inferiores\",'A');");
		negrice.add("insert into consulta (id_consultas,id_agendamento,desc_consulta,pagamento) values"
				+ "(4,4,\"Manutenção de dentes inferiores\",'P');");
		negrice.add("insert into consulta (id_consultas,id_agendamento,desc_consulta,pagamento) values"
				+ "(5,5,\"Tratamento de dentes inferiores\",'D');");

		negrice.add("insert into itens_servico_consulta(Servico_id_servico,Consulta_id_consultas,qut_servico,preco_total) values (1,1,3,'450');");
		negrice.add("insert into itens_servico_consulta(Servico_id_servico,Consulta_id_consultas,qut_servico,preco_total) values (2,1,3,'300');");
		negrice.add("insert into itens_servico_consulta(Servico_id_servico,Consulta_id_consultas,qut_servico,preco_total) values (3,1,3,'150');");
		negrice.add("insert into itens_servico_consulta(Servico_id_servico,Consulta_id_consultas,qut_servico,preco_total) values (4,1,3,'240.00');");
		negrice.add("insert into itens_servico_consulta(Servico_id_servico,Consulta_id_consultas,qut_servico,preco_total) values (1,2,3,'450');");

		try {
			conexao.setAutoCommit(false);
			PreparedStatement pst = conexao.prepareStatement("?");
			for (String nedgrao : negrice) {
				pst.setString(1, nedgrao);
				pst.execute(nedgrao);
			}
			conexao.commit();
			JOptionPane.showMessageDialog(null, "TABELAS PREENCHIDAS !");
			return true;
		} catch (Exception e) {
			conexao.rollback();
			throw new BancoDadosException(e.getMessage(),EBancoDadosException.INSERE_DADO);
		}  finally {
			bd.desconectar();
		}
	}

}
