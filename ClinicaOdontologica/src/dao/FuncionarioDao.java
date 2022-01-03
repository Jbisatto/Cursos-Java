package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bancoDeDados.BancoDadosException;
import bancoDeDados.BancoDeDados;
import bancoDeDados.EBancoDadosException;
import bancoDeDados.IPadraoDAO;
import beans.Funcionario;
import enumerador.ESexo;
import enumerador.ETipo;

public class FuncionarioDao implements IPadraoDAO {
	BancoDeDados bd = new BancoDeDados();
	

	@Override
	public boolean criaTabela() throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			Statement st = conexao.createStatement();

			return st.execute("CREATE TABLE funcionario (" 
					+  " id_funcionario INT(5) NOT NULL AUTO_INCREMENT,"
					+ " desc_funcionario VARCHAR(50) NOT NULL,"
					+ " email VARCHAR(50) NULL DEFAULT NULL,"
					+ " telefone BIGINT(10) NOT NULL,"
					+ " sexo VARCHAR(20) NOT NULL,cpf BIGINT(11) NOT NULL,"
					+ " tipo_funcionario VARCHAR(50) NULL DEFAULT NULL,"
					+ " PRIMARY KEY (id_funcionario));");
		
			
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
			st.execute("DROP TABLE funcionario;");
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			bd.desconectar();
		}
	}

	public boolean insere(Funcionario funcionario) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao
					.prepareStatement("INSERT INTO funcionario( "
							+ "desc_funcionario,tipo_funcionario,email,telefone,sexo,cpf)" 
							+ "VALUES (?,?,?,?,?,?);");
			pst.setString(1, funcionario.getNome());
			pst.setString(2, String.valueOf(funcionario.getTipo().getDescricao()));
			pst.setString(3, funcionario.getEmail());
			pst.setInt(4, funcionario.getTelefone());
			pst.setString(5, String.valueOf(funcionario.getSexo().getDescricao()));
			pst.setInt(6, funcionario.getCpf());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.INSERE_DADO);
		} finally {
			bd.desconectar();
		}
	}

	public Funcionario consulta(int id_funcionario) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"SELECT * FROM funcionario WHERE id_funcionario = ?;");
			pst.setInt(1, id_funcionario);
			ResultSet rs = pst.executeQuery();
			return rs.first() ? new Funcionario(
					rs.getInt("id_funcionario"),
					((rs.getString("tipo_funcionario")).toUpperCase().charAt(0))=='S'?ETipo.SECRETARIA:ETipo.ADMINISTRADOR,
					rs.getString("desc_funcionario"),
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

	public boolean alterarFuncionario(Funcionario funcionario) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao
					.prepareStatement(
							"UPDATE Funcionario SET "
							+ "desc_funcionario = ?, tipo_funcionario = ?,"
							+ " email = ?,telefone = ?,sexo = ?,cpf = ? "
							+ "WHERE id_funcionario = ?;");
			pst.setString(1, funcionario.getNome());
			pst.setString(2, String.valueOf(funcionario.getTipo().getDescricao()));
			pst.setString(3, funcionario.getEmail());
			pst.setInt(4, funcionario.getTelefone());
			pst.setString(5, String.valueOf(funcionario.getSexo().getDescricao()));
			pst.setInt(6, funcionario.getCpf());
			pst.setInt(7, funcionario.getId());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.ATUALIZA_DADO);
		} finally {
			bd.desconectar();
		}
	}

	public List<Funcionario> consulta() throws BancoDadosException {
		Connection conexao = bd.conectar();
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Funcionario;");
			while (rs.next()) {
				funcionarios.add(new Funcionario(
						rs.getInt("id_funcionario"),
						((rs.getString("tipo_funcionario")).toUpperCase().charAt(0))=='S'?ETipo.SECRETARIA:ETipo.ADMINISTRADOR,
						rs.getString("desc_funcionario"),
					    rs.getInt("cpf"),
						rs.getInt("telefone"),
						rs.getString("email"), 
						((rs.getString("sexo")).toUpperCase().charAt(0))=='M'?ESexo.MASCULINO:ESexo.FEMININO));
			}
			return funcionarios;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.CONSULTA_DADO);
		} finally {
			bd.desconectar();
		}
	}
	public List<Funcionario> consulta(String nome) throws BancoDadosException {
		Connection conexao = bd.conectar();
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Funcionario WHERE desc_funcionario LIKE'%"+nome+"%';");
			while (rs.next()) {
				funcionarios.add(new Funcionario(
						rs.getInt("id_funcionario"),
						((rs.getString("tipo_funcionario")).toUpperCase().charAt(0))=='S'?ETipo.SECRETARIA:ETipo.ADMINISTRADOR,
						rs.getString("desc_funcionario"),
					    rs.getInt("cpf"),
						rs.getInt("telefone"),
						rs.getString("email"), 
						((rs.getString("sexo")).toUpperCase().charAt(0))=='M'?ESexo.MASCULINO:ESexo.FEMININO));
			}
			return funcionarios;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.CONSULTA_DADO);
		} finally {
			bd.desconectar();
		}
	}

	public boolean insere(List<Funcionario> funcionarios) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao.prepareStatement("INSERT INTO funcionario( "
					+ "(desc_funcionario,tipo_funcionario,email,telefone,sexo,cpf)" 
					+ "		   VALUES (?,?,?,?,?,?);");
			for (Funcionario funcionario : funcionarios) {
				pst.setString(1, funcionario.getNome());
				pst.setString(2, String.valueOf(funcionario.getTipo().getDescricao()));
				pst.setString(3, funcionario.getEmail());
				pst.setInt(4, funcionario.getTelefone());
				pst.setString(5, String.valueOf(funcionario.getSexo().getDescricao()));
				pst.setInt(6, funcionario.getCpf());
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

	public boolean excluirFuncionario(int id_funcionario) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao
					.prepareStatement("DELETE FROM funcionario WHERE id_funcionario = ?;");
			pst.setInt(1, id_funcionario);
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.EXLUI_DADO);
		} finally {
			bd.desconectar();
		}
	}

}
