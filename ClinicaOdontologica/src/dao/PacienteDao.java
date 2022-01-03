package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bancoDeDados.BancoDadosException;
import bancoDeDados.BancoDeDados;
import bancoDeDados.EBancoDadosException;
import bancoDeDados.IPadraoDAO;
import beans.Paciente;
import enumerador.ESexo;

public class PacienteDao implements IPadraoDAO {
	BancoDeDados bd = new BancoDeDados();


	@Override
	public boolean criaTabela() throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			Statement st = conexao.createStatement();
			st.execute("CREATE TABLE paciente (								 "
					+ "	id_paciente INT(5) NOT NULL AUTO_INCREMENT, "
					+ "	desc_paciente VARCHAR(50) NOT NULL,		     "
					+ "	email VARCHAR(50) NULL DEFAULT NULL,         "
					+ "	telefone BIGINT(10) NOT NULL,				 "
					+ "	sexo VARCHAR(20) NOT NULL,					     "
					+ "	cpf BIGINT(11) NOT NULL,					 "
					+ "	dt_nascimento DATE NULL DEFAULT NULL,        "
					+ "	convenio VARCHAR(50) NULL DEFAULT NULL,      "
					+ "	PRIMARY KEY (id_paciente))");
			return true;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.CRIA_TABELA);
		} finally {
			bd.desconectar();
		}
	}

	@Override
	public boolean destroiTabela() throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			Statement st = conexao.createStatement();
			st.execute("DROP TABLE paciente;");
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			bd.desconectar();
		}
	}

	public boolean insere(Paciente paciente) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao
					.prepareStatement("INSERT INTO paciente( "
							+ "		 	 desc_paciente, email,"
							+ "			 telefone, sexo, cpf,dt_nascimento,"
							+ "			 convenio)"

							+ "		   VALUES (?, ?, ?, ?, ?,?,?);");
			pst.setString(1, paciente.getNome());
			pst.setString(2, paciente.getEmail());
			pst.setInt(3, paciente.getTelefone());
			pst.setString(4, String.valueOf(paciente.getSexo().getDescricao()));
			pst.setInt(5, paciente.getCpf());
			pst.setDate(6, new Date(paciente.getDt_nascimento().getTime()));
			pst.setString(7, paciente.getConvenio());

			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.INSERE_DADO);
		} finally {
			bd.desconectar();
		}
	}

	public Paciente consulta(int id_paciente) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"SELECT * FROM paciente WHERE id_paciente = ?;");
			pst.setInt(1, id_paciente);
			ResultSet rs = pst.executeQuery();
			return rs.first() ? new Paciente(
					rs.getInt("id_paciente"),
				    rs.getString("convenio"), 
				    rs.getDate("dt_nascimento"),
					rs.getString("desc_paciente"),
					rs.getInt("cpf"),
					rs.getInt("telefone"),
					rs.getString("email"), 
					((rs.getString("sexo")).toUpperCase().charAt(0))=='M'?ESexo.MASCULINO:ESexo.FEMININO) : null;

		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.CONSULTA_DADO);
		} finally {
			bd.desconectar();
		}
	}
	

	public boolean alterarPaciente(Paciente paciente) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao
					.prepareStatement("UPDATE Paciente SET "
							+ "desc_paciente = ?, email = ?, telefone = ?, "
							+ "sexo = ?, cpf = ?, dt_nascimento = ?, convenio = ? "
							+ "WHERE id_paciente = ?;");
			pst.setString(1, paciente.getNome());
			pst.setString(2, paciente.getEmail());
			pst.setInt(3, paciente.getTelefone());
			pst.setString(4, String.valueOf(paciente.getSexo().getDescricao()));
			pst.setInt(5, paciente.getCpf());
			pst.setDate(6, new Date(paciente.getDt_nascimento().getTime()));
			pst.setString(7, paciente.getConvenio());
			pst.setInt(8, paciente.getId());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.ATUALIZA_DADO);
		} finally {
			bd.desconectar();
		}
	}

	public List<Paciente> consulta() throws BancoDadosException {
		Connection conexao = bd.conectar();
		List<Paciente> pacientes = new ArrayList<Paciente>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Paciente;");
			while (rs.next()) {
				pacientes.add(new Paciente(
						rs.getInt("id_paciente"),
					    rs.getString("convenio"), 
					    rs.getDate("dt_nascimento"),
						rs.getString("desc_paciente"), 
						rs.getInt("cpf"), 
						rs.getInt("telefone"),
						rs.getString("email"), 
						((rs.getString("sexo")).toUpperCase().charAt(0))=='M'?ESexo.MASCULINO:ESexo.FEMININO));
			}
			return pacientes;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.CONSULTA_DADO);
		} finally {
			bd.desconectar();
		}
	}
	public List<Paciente> consulta(String nome) throws BancoDadosException {
		Connection conexao = bd.conectar();
		List<Paciente> pacientes = new ArrayList<Paciente>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Paciente WHERE desc_paciente LIKE'%"+nome+"%';");
			while (rs.next()) {
				pacientes.add(new Paciente(
						rs.getInt("id_paciente"),
					    rs.getString("convenio"), 
					    rs.getDate("dt_nascimento"),
						rs.getString("desc_paciente"), 
						rs.getInt("cpf"), 
						rs.getInt("telefone"),
						rs.getString("email"), 
						((rs.getString("sexo")).toUpperCase().charAt(0))=='M'?ESexo.MASCULINO:ESexo.FEMININO));
			}
			return pacientes;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.CONSULTA_DADO);
		} finally {
			bd.desconectar();
		}
	}

	public boolean insere(List<Paciente> pacientes) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao.prepareStatement(
									"INSERT INTO paciente( "
							+ "		 	desc_paciente, email,"
							+ "			telefone, sexo, cpf,dt_nascimento,"
							+ "			convenio						  "            
							+ "		)            						  "
							+ "		VALUES (?, ?, ?, ?, ?, ?, ?);");
			for (Paciente paciente : pacientes) {
				pst.setString(1, paciente.getNome());
				pst.setString(2, paciente.getEmail());
				pst.setInt(3, paciente.getTelefone());
				pst.setString(4, String.valueOf(paciente.getSexo().getSigla()));
				pst.setInt(5, paciente.getCpf());
				pst.setDate(6, new Date(paciente.getDt_nascimento().getTime()));
				pst.setString(7, paciente.getConvenio());
				pst.executeUpdate();
			}
			return true;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.INSERE_DADO);
		} finally {
			bd.desconectar();
		}
	}

	public boolean excluirPaciente(int id_paciente) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao
					.prepareStatement("DELETE FROM paciente WHERE id_paciente = ?;");
			pst.setInt(1, id_paciente);
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.EXLUI_DADO);
		} finally {
			bd.desconectar();
		}
	}
	public void inserirPaciente(Paciente paciente) {
		
		
	}

}
