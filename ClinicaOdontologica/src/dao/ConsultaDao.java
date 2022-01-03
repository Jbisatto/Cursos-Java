package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import enumerador.EPagamento;
import bancoDeDados.BancoDadosException;
import bancoDeDados.BancoDeDados;
import bancoDeDados.EBancoDadosException;
import bancoDeDados.IPadraoDAO;
import beans.Consulta;

public class ConsultaDao implements IPadraoDAO {
	BancoDeDados bd = new BancoDeDados();

	@Override
	public boolean criaTabela() throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			Statement st = conexao.createStatement();
			st.execute("CREATE TABLE consulta ("
					+ "id_consultas INT(5) NOT NULL,"
					+ "id_agendamento INT(5) NOT NULL,"
					+ "desc_consulta VARCHAR(150) NOT NULL,"
					+ "`pagamento` CHAR NOT NULL,"
					+ "PRIMARY KEY (id_consultas),"
					+ "INDEX FK_consulta_agendamento (id_agendamento), "
					+ "CONSTRAINT FK_consulta_agendamento "
					+ "FOREIGN KEY (id_agendamento)"
					+ "REFERENCES agendamento (id_agendamento));");

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
			st.execute("DROP TABLE consulta;");
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			bd.desconectar();
		}
	}

	public boolean insere(Consulta consulta) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao
					.prepareStatement("INSERT INTO consulta    "
							+ " (id_consultas,id_agendamento, desc_consulta,pagamento) "
							+ "VALUES " + "		(?,?, ?,?);");
			pst.setInt(1,consulta.getId_consulta());
			pst.setInt(2, consulta.getId_agendamento());
			pst.setString(3, consulta.getDescricaoConsulta());
			pst.setString(4, String.valueOf(consulta.getPagamento().getSigla()));
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.INSERE_DADO);
		} finally {
			bd.desconectar();
		}
	}

	public Consulta consulta(int id_consulta) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao
					.prepareStatement("SELECT * FROM consulta where "
							+ "id_consultas = ?");
			pst.setInt(1, id_consulta);
			ResultSet rs = pst.executeQuery();
			return rs.first() ? new Consulta(
					rs.getInt("id_consultas"),
					rs.getInt("id_agendamento"),
					rs.getString("desc_consulta"),
					((rs.getString("pagamento").charAt(0)) == 'P' ? EPagamento.PAGO
							: (rs.getString("pagamento").charAt(0)) == 'D' ? EPagamento.DEVE
									: EPagamento.ABERTO))
					: null;

		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.CONSULTA_DADO);
		} finally {
			bd.desconectar();
		}
	}

	public boolean altera(Consulta consulta) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao
					.prepareStatement("UPDATE consulta SET "
							+ " id_agendamento = ?, desc_consulta = ?,pagamento = ?"
							+ "WHERE  Id_Consultas = ?");
			pst.setInt(1, consulta.getId_agendamento());
			pst.setString(2, consulta.getDescricaoConsulta());
			pst.setString(3, String.valueOf(consulta.getPagamento().getSigla()));
			pst.setInt(4, consulta.getId_consulta());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.ATUALIZA_DADO);
		} finally {
			bd.desconectar();
		}
	}
	public boolean alteraPagamento(int id_consulta,char pagamento) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao
					.prepareStatement("UPDATE consulta SET pagamento = ?"
							+ "WHERE  Id_Consultas = ?");
			pst.setString(1,String.valueOf(pagamento));
			pst.setInt(2,id_consulta);
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.ATUALIZA_DADO);
		} finally {
			bd.desconectar();
		}
	}

	public List<Consulta> consulta() throws BancoDadosException {
		Connection conexao = bd.conectar();
		List<Consulta> consultas = new ArrayList<Consulta>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM consulta ;");
			while (rs.next()) {
				consultas.add(new Consulta(rs.getInt("id_consultas"), rs
						.getInt("id_agendamento"), rs
						.getString("desc_consulta"),
						((rs.getString("pagamento").charAt(0)) == 'P' ? EPagamento.PAGO
								: (rs.getString("pagamento").charAt(0)) == 'D' ? EPagamento.DEVE
										: EPagamento.ABERTO)));
			}
			return consultas;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.CONSULTA_DADO);
		} finally {
			bd.desconectar();
		}
	}

	public List<Consulta> consulta(String nome) throws BancoDadosException {
		Connection conexao = bd.conectar();
		List<Consulta> consultas = new ArrayList<Consulta>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st
					.executeQuery("SELECT * FROM consulta WHERE id_consultas LIKE'%"
							+ nome + "%';");
			while (rs.next()) {
				consultas.add(new Consulta(rs.getInt("id_consultas"), rs
						.getInt("id_agendamento"), rs
						.getString("desc_consulta"),
						((rs.getString("pagamento").charAt(0)) == 'P' ? EPagamento.PAGO
								: (rs.getString("pagamento").charAt(0)) == 'D' ? EPagamento.DEVE
										: EPagamento.ABERTO)));
			}
			return consultas;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.CONSULTA_DADO);
		} finally {
			bd.desconectar();
		}
	}

	public List<Consulta> consultaAgendamento(String nome)
			throws BancoDadosException {
		Connection conexao = bd.conectar();
		List<Consulta> consultas = new ArrayList<Consulta>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st
					.executeQuery("SELECT * FROM consulta WHERE id_agendamento LIKE'%"
							+ nome + "%';");
			while (rs.next()) {
				consultas.add(new Consulta(rs.getInt("id_consultas"), rs
						.getInt("id_agendamento"), rs
						.getString("desc_consulta"),
						((rs.getString("pagamento").charAt(0)) == 'P' ? EPagamento.PAGO
								: (rs.getString("pagamento").charAt(0)) == 'D' ? EPagamento.DEVE
										: EPagamento.ABERTO)));
			}
			return consultas;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.CONSULTA_DADO);
		} finally {
			bd.desconectar();
		}
	}

	public boolean insere(List<Consulta> consultas) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao
					.prepareStatement("INSERT INTO consulta "
							+ "(id_agendamento, desc_consulta,pagamento) " + "VALUES "
							+ " 	(?, ?,?);");
			for (Consulta consulta : consultas) {
				pst.setInt(1, consulta.getId_consulta());
				pst.setString(2, consulta.getDescricaoConsulta());
				pst.setString(3, String.valueOf(consulta.getPagamento().getSigla()));
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

	public boolean exclui(int id_consulta) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao
					.prepareStatement("DELETE FROM Consulta WHERE id_consultas = ?;");
			pst.setInt(1, id_consulta);
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.EXLUI_DADO);
		} finally {
			bd.desconectar();
		}
	}
}
