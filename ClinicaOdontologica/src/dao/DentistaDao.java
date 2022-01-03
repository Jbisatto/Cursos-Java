package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import enumerador.ESexo;
import bancoDeDados.BancoDadosException;
import bancoDeDados.BancoDeDados;
import bancoDeDados.EBancoDadosException;
import bancoDeDados.IPadraoDAO;
import beans.Dentista;

public class DentistaDao implements IPadraoDAO {
	BancoDeDados bd = new BancoDeDados();
	@Override
	public boolean criaTabela() throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {			
			Statement st = conexao.createStatement();
			st.execute("CREATE TABLE dentista (								 "
						+	"	id_dentista INT(5) NOT NULL AUTO_INCREMENT, "
						+	"	desc_dentista VARCHAR(50) NOT NULL,		     "	
						+	"	email VARCHAR(50) NULL DEFAULT NULL,         " 
						+	"	telefone BIGINT(10) NOT NULL,				 "	 				
						+	"	cro BIGINT(20) NOT NULL,					 "	
						+	"	cpf BIGINT(11) NOT NULL,					 "
						+	"	sexo VARCHAR(20) NULL DEFAULT NULL,         "
						+	"	PRIMARY KEY (id_dentista))");		
			return true;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(), EBancoDadosException.CRIA_TABELA);
		} finally {
			bd.desconectar();
		}
	}

	@Override
	public boolean destroiTabela() throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			Statement st = conexao.createStatement();
			st.execute("DROP TABLE dentista;");
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			bd.desconectar();
		}
	}
	
	public boolean insere(Dentista dentista) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao.prepareStatement(
			"INSERT INTO dentista "
		          + "(desc_dentista, email, telefone, cro, cpf,sexo) "
		    +"VALUES "
		    + "		( ?, ?, ?, ?, ?,?);");
			pst.setString(1, dentista.getNome());
			pst.setString(2, dentista.getEmail());
			pst.setInt(3, dentista.getTelefone());
			pst.setInt(4, dentista.getCro());
			pst.setInt(5, dentista.getCpf());
			pst.setString(6, String.valueOf(dentista.getSexo().getDescricao()));
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(), EBancoDadosException.INSERE_DADO);
		} finally {
			bd.desconectar();
		}
	}

	public Dentista consulta(int id_dentista) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM dentista WHERE id_dentista = ?;");
			pst.setInt(1, id_dentista);
			ResultSet rs = pst.executeQuery();
			return rs.first() ? new Dentista(
					                         rs.getInt("id_dentista"),
					                         rs.getInt("cro"),										     
										     rs.getString("desc_dentista"),
										     rs.getInt("cpf"),
										     rs.getInt("telefone"),			
										     rs.getString("email"),										     
										     ((rs.getString("sexo")).toUpperCase().charAt(0))=='M'?ESexo.MASCULINO:ESexo.FEMININO) : null;										     
												
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(), EBancoDadosException.CONSULTA_DADO);
		} finally {
			bd.desconectar();
		}
	}
	
	public boolean altera(Dentista dentista) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao.prepareStatement("UPDATE Dentista SET desc_dentista = ?, email = ?, telefone = ?, cro = ?, cpf = ? WHERE id_dentista = ?;");
			pst.setString(1, dentista.getNome());
			pst.setString(2, dentista.getEmail());
			pst.setInt(3, dentista.getTelefone());
			pst.setInt(4, dentista.getCro());
			pst.setInt(5, dentista.getCpf());
			pst.setInt(6, dentista.getId());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(), EBancoDadosException.ATUALIZA_DADO);
		} finally {
			bd.desconectar();
		}
	}
	
	public List<Dentista> consulta() throws BancoDadosException {
		Connection conexao = bd.conectar();
		List<Dentista> dentistas = new ArrayList<Dentista>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Dentista;");
			while (rs.next()) {
				dentistas.add(new Dentista(
						   rs.getInt("id_dentista"),
						   rs.getInt("cro"),
						   rs.getString("desc_dentista"),
						   rs.getInt("cpf"),
						   rs.getInt("telefone"),						   
						   rs.getString("email"),
						   ((rs.getString("sexo")).toUpperCase().charAt(0))=='M'?ESexo.MASCULINO:ESexo.FEMININO));
	}
			return dentistas;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(), EBancoDadosException.CONSULTA_DADO);
		} finally {
			bd.desconectar();
		}
	}
	
	public List<Dentista> consulta(String nome) throws BancoDadosException {
		Connection conexao = bd.conectar();
		List<Dentista> dentistas = new ArrayList<Dentista>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Dentista WHERE desc_dentista LIKE'%"+nome+"%';");
			while (rs.next()) {
				dentistas.add(new Dentista(
						   rs.getInt("id_dentista"),
						   rs.getInt("cro"),
						   rs.getString("desc_dentista"),
						   rs.getInt("cpf"),
						   rs.getInt("telefone"),						   
						   rs.getString("email"),
						   ((rs.getString("sexo")).toUpperCase().charAt(0))=='M'?ESexo.MASCULINO:ESexo.FEMININO));
	}
			return dentistas;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(), EBancoDadosException.CONSULTA_DADO);
		} finally {
			bd.desconectar();
		}
	}
	
	public boolean insere(List<Dentista> dentistas) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao.prepareStatement("INSERT INTO Dentista (id_dentista, desc_dentista, email, telefone, cro, cpf) VALUES (?, ?, ?, ?, ?, ?);");
			for (Dentista dentista : dentistas) {
				pst.setInt(1, dentista.getId());
				pst.setString(2, dentista.getNome());
				pst.setString(3, dentista.getEmail());
				pst.setInt(4, dentista.getTelefone());
				pst.setInt(5, dentista.getCro());
				pst.setInt(6, dentista.getCpf());				
				pst.executeUpdate();
			}
			return true;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(), EBancoDadosException.INSERE_DADO);
		} finally {
			bd.desconectar();
		}
	}


	public boolean exclui(int id_dentista) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM Dentista WHERE id_dentista = ?;");
			pst.setInt(1, id_dentista);
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(), EBancoDadosException.EXLUI_DADO);
		} finally {
			bd.desconectar();
		}
	}

}
