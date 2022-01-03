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
import beans.ItensServicoConsulta;

public class ItensServicoConsultaDAO implements IPadraoDAO {
	BancoDeDados bd = new BancoDeDados();
	@Override
	public boolean criaTabela() throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			Statement st = conexao.createStatement();
			st.execute("CREATE TABLE itens_servico_consulta (					 "
					+ "	Servico_id_servico INT(5) NOT NULL,	    				 "
					+ "	Consulta_id_consultas INT(5) NOT NULL, 					 "
					+ "	qut_servico INT(5) NULL DEFAULT NULL,   				 "
					+ "	preco_total FLOAT(10) NULL DEFAULT NULL, 				 "
					+ "	PRIMARY KEY (Servico_id_servico, Consulta_id_consultas), "
					+ "	INDEX FK_itens_servico_consulta_consulta				 "
					+ " 				(Consulta_id_consultas),					 "
					+ " CONSTRAINT FK_itens_servico_consulta_servico FOREIGN KEY "
					+ "	    (Servico_id_servico) REFERENCES servico (id_servico),"
					+ " CONSTRAINT FK_itens_servico_consulta_consulta FOREIGN KEY" 
					+ " 	(Consulta_id_consultas) REFERENCES consulta 		 "
					+ "											(id_consultas));");
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
			st.execute("DROP TABLE itens_servico_consulta;");
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			bd.desconectar();
		}
	}
	
	public float buscaPrecoTotal(int id_servico, int qut_servico) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao
					.prepareStatement("SELECT preco FROM servico WHERE id_servico = ?;");
			pst.setInt(1,id_servico);
			ResultSet rs = pst.executeQuery();
			return rs.first() ?(rs.getFloat("preco") * qut_servico):null;										
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.CONSULTA_DADO);
		} finally {
			bd.desconectar();
		}
		
	}	
	
	public List<ItensServicoConsulta> consulta() throws BancoDadosException {
		Connection conexao = bd.conectar();
		List<ItensServicoConsulta> iscs = new ArrayList<ItensServicoConsulta>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM itens_servico_consulta ORDER BY Consulta_id_consultas ;");
			while (rs.next()) {
				iscs.add(new ItensServicoConsulta(rs.getInt("Consulta_id_consultas"),
						rs.getInt("Servico_id_servico"),rs.getInt("qut_servico"), rs
						.getFloat("preco_total")));
			}
			return iscs;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.CONSULTA_DADO);
		} finally {
			bd.desconectar();
		}
	}
	public List<ItensServicoConsulta> consulta(int nome) throws BancoDadosException {
		Connection conexao = bd.conectar();
		List<ItensServicoConsulta> iscs = new ArrayList<ItensServicoConsulta>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM itens_servico_consulta WHERE Consulta_id_consultas LIKE'%"+nome+"%' ORDER BY Consulta_id_consultas ;");
			while (rs.next()) {
				iscs.add(new ItensServicoConsulta(rs.getInt("Consulta_id_consultas"),
						rs.getInt("Servico_id_servico"),rs.getInt("qut_servico"), rs
						.getFloat("preco_total")));
			}
			return iscs;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.CONSULTA_DADO);
		} finally {
			bd.desconectar();
		}
	}
	
	public boolean inserir(ItensServicoConsulta isc) throws BancoDadosException{
			Connection conexao = bd.conectar();
			try {
				PreparedStatement pst = conexao
						.prepareStatement("INSERT INTO itens_servico_consulta "
								+ "(Servico_id_servico,Consulta_id_consultas,"
								+ "qut_servico,preco_total) "
								+ "VALUES " + "		(?, ?, ?, ?);");
				pst.setInt(1, isc.getId_servico());
				pst.setInt(2, isc.getId_consulta());
				pst.setInt(3, isc.getQut_servico());
				pst.setFloat(4, isc.getPreco_total());
				return pst.executeUpdate() > 0;
			} catch (Exception e) {
				throw new BancoDadosException(e.getMessage(),
						EBancoDadosException.INSERE_DADO);
			} finally {
				bd.desconectar();
			}
		}
	
	
	public ItensServicoConsulta consultar(int id_consulta,int id_servico) throws BancoDadosException{
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao
					.prepareStatement("SELECT * FROM itens_servico_consulta WHERE Servico_id_servico = ? and Consulta_id_consultas = ?;");
			pst.setInt(1, id_servico);
			pst.setInt(2, id_consulta);
			ResultSet rs = pst.executeQuery();
			return rs.first() ? new ItensServicoConsulta(rs.getInt("Consulta_id_consultas"),
					rs.getInt("Servico_id_servico"),rs.getInt("qut_servico"), rs.getFloat("preco_total")): null;

		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.CONSULTA_DADO);
		} finally {
			bd.desconectar();
		}
		
	}
	
	public boolean alterar(ItensServicoConsulta isc) throws BancoDadosException{	
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao
					.prepareStatement(
					 "UPDATE itens_servico_consulta"
				    +"SET"  
					+ 	 "Servico_id_servico = ?, Consulta_id_consultas = ?,"
					+ 	 "qut_servico = ? preco_total = ?) " 
					+"WHERE "
					+ 	 "Servico_Id_Servico = ? AND"
					+    "Consulta_Id_Consultas = ?");						
			pst.setInt(1, isc.getId_servico());
			pst.setInt(2, isc.getId_consulta());
			pst.setInt(3, isc.getQut_servico());
			pst.setFloat(4, isc.getPreco_total());
			pst.setInt(5, isc.getId_servico());
			pst.setInt(6, isc.getId_consulta());			
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.ATUALIZA_DADO);
		} finally {
			bd.desconectar();
		}				
	}
	

	public boolean inserir(List<ItensServicoConsulta> iscs) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao
					.prepareStatement(
							"INSERT INTO itens_servico_consulta "
						   +	 "(Servico_id_servico,Consulta_id_consultas,"
						   +	 "qut_servico,preco_total) "
					       +"VALUES (?, ?, ?, ?);");
			for (ItensServicoConsulta isc : iscs) {
				pst.setInt(1, isc.getId_servico());
				pst.setInt(2, isc.getId_consulta());
				pst.setInt(3, isc.getQut_servico());
				pst.setFloat(4, isc.getPreco_total());
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

	public boolean exclui(int id_consulta,int id_servico) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao
					.prepareStatement(
							"DELETE FROM "
							+ 	 "itens_servico_consulta "
							+"WHERE "
							+ 	 "Servico_Id_Servico = ? AND"
							+    "Consulta_Id_Consultas = ?");
			pst.setInt(1, id_servico);
			pst.setInt(2, id_consulta);
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.EXLUI_DADO);
		} finally {
			bd.desconectar();
		}
	}


}
