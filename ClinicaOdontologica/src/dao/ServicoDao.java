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
import beans.Servico;

public class ServicoDao implements IPadraoDAO{
	BancoDeDados bd = new BancoDeDados();
	@Override
	public boolean criaTabela() throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {			
			Statement st = conexao.createStatement();
			st.execute("CREATE TABLE Servico (								 "
						+	"	id_servico INT(5) NOT NULL AUTO_INCREMENT, "
						+	"	desc_servico VARCHAR(50) NOT NULL,		     "		
						+	"	preco float NOT NULL,					 "			
						+	"	PRIMARY KEY (id_servico))");		
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
			st.execute("DROP TABLE servico;");
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			bd.desconectar();
		}
	}
	
	public boolean insere(Servico servico) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao.prepareStatement(
			"INSERT INTO servico "
		          + "(desc_servico, preco) "
		    +"VALUES "
		    + "		(?, ?);");
			pst.setString(1, servico.getNomeServico());
			pst.setFloat(2, servico.getPrecoServico());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(), EBancoDadosException.INSERE_DADO);
		} finally {
			bd.desconectar();
		}
	}

	public Servico consulta(int id_servico) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao.prepareStatement(
							"SELECT * FROM servico WHERE id_servico = ?;");
			pst.setInt(1, id_servico);
			ResultSet rs = pst.executeQuery();
			return rs.first() ? new Servico(
				                         rs.getInt("id_servico"),
									     rs.getString("desc_servico"),
									     rs.getFloat("preco")) : null;										     
												
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(), EBancoDadosException.CONSULTA_DADO);
		} finally {
			bd.desconectar();
		}
	}
	
	public boolean altera(Servico servico) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao.prepareStatement("UPDATE servico SET desc_servico = ?, preco = ? WHERE id_servico = ?;");
			pst.setString(1, servico.getNomeServico());
			pst.setFloat(2, servico.getPrecoServico());
			pst.setInt(3, servico.getId_servico());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(), EBancoDadosException.ATUALIZA_DADO);
		} finally {
			bd.desconectar();
		}
	}
	
	public List<Servico> consulta() throws BancoDadosException {
		Connection conexao = bd.conectar();
		List<Servico> servicos = new ArrayList<Servico>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM servico;");
			while (rs.next()) {
				servicos.add(new Servico(
						   rs.getInt("id_servico"),
						   rs.getString("desc_servico"),
						   rs.getFloat("preco")));
	}
			return servicos;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(), EBancoDadosException.CONSULTA_DADO);
		} finally {
			bd.desconectar();
		}
	}
	public List<Servico> consulta(String nome) throws BancoDadosException {
		Connection conexao = bd.conectar();
		List<Servico> servicos = new ArrayList<Servico>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM servico WHERE desc_servico LIKE'%"+nome+"%';");
			while (rs.next()) {
				servicos.add(new Servico(
						   rs.getInt("id_servico"),
						   rs.getString("desc_servico"),
						   rs.getFloat("preco")));
	}
			return servicos;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(), EBancoDadosException.CONSULTA_DADO);
		} finally {
			bd.desconectar();
		}
	}
	
	public boolean insere(List<Servico> servicos) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao.prepareStatement(
						"INSERT INTO Servico (desc_servico, preco) "
					  + "VALUES (?, ?);");
			for (Servico servico : servicos) {
				pst.setString(1, servico.getNomeServico());
				pst.setFloat(2, servico.getPrecoServico());			
				pst.executeUpdate();
			}
			return true;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(), EBancoDadosException.INSERE_DADO);
		} finally {
			bd.desconectar();
		}
	}


	public boolean exclui(int id_servico) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM Servico WHERE id_servico = ?;");
			pst.setInt(1, id_servico);
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(), EBancoDadosException.EXLUI_DADO);
		} finally {
			bd.desconectar();
		}
	}

}
