package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import enumerador.EConsulta;
import bancoDeDados.BancoDadosException;
import bancoDeDados.BancoDeDados;
import bancoDeDados.EBancoDadosException;
import bancoDeDados.IPadraoDAO;
import beans.Agendamento;

public class AgendamentoDao implements IPadraoDAO {
	BancoDeDados bd = new BancoDeDados();

	@Override
	public boolean criaTabela() throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			Statement st = conexao.createStatement();
			st.execute("CREATE TABLE `agendamento` ("
					+ "`id_agendamento` INT(5) NOT NULL AUTO_INCREMENT,"
					+ "`id_dentista` INT(5) NOT NULL,"
					+ "`dt_agendamento` DATE NOT NULL,"
					+ "`hr_agendamento` TIME NOT NULL,"
					+ "`Funcionario_id_funcionario` INT(5) NOT NULL,"
					+ "`Paciente_id_paciente` INT(5) NOT NULL,"
					+ "`consulta_realizada` CHAR NOT NULL,"
					+ "PRIMARY KEY (id_agendamento),"
					+ "INDEX `Paciente_id_paciente` (`Paciente_id_paciente`),"
					+ "CONSTRAINT `agendamento_ibfk_1` FOREIGN KEY (`Paciente_id_paciente`) REFERENCES `paciente` (`id_paciente`) ON UPDATE NO ACTION ON DELETE NO ACTION,"
					+ "CONSTRAINT `agendamento_ibfk_2` FOREIGN KEY (`id_dentista`) REFERENCES `dentista` (`id_dentista`) ON UPDATE NO ACTION ON DELETE NO ACTION,"
					+ "CONSTRAINT `agendamento_ibfk_3` FOREIGN KEY(Funcionario_id_funcionario) REFERENCES Funcionario(id_funcionario) ON DELETE NO ACTION ON UPDATE NO ACTION);");
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
			st.execute("DROP TABLE agendamento;");
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			bd.desconectar();
		}
	}

	public boolean insere(Agendamento agendamento) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao
					.prepareStatement("INSERT INTO agendamento "
							+ "(id_dentista,dt_agendamento,hr_agendamento,Funcionario_id_funcionario,Paciente_id_paciente,consulta_realizada) "
							+ "VALUES " + "		(?, ?,?, ?,?,?);");
			pst.setInt(1, agendamento.getId_dentista());
			pst.setDate(2, new Date(agendamento.getDt_agendamento().getTime()));
			pst.setTime(3, new Time(agendamento.getHr_agendamento().getTime()));
			pst.setInt(4, agendamento.getId_funcionario());
			pst.setInt(5, agendamento.getId_paciente());
			pst.setString(6, String.valueOf(agendamento.getConsultaRealizada()
					.getSigla()));
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.INSERE_DADO);
		} finally {
			bd.desconectar();
		}
	}

	public Agendamento consulta(int id_agendamento) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao
					.prepareStatement("SELECT * FROM agendamento WHERE id_agendamento = ?");
			pst.setInt(1, id_agendamento);
			ResultSet rs = pst.executeQuery();
			return rs.first() ? new Agendamento(
					rs.getInt("id_agendamento"),
					rs.getInt("id_dentista"),
					rs.getDate("dt_agendamento"),
					rs.getTime("hr_agendamento"),
					rs.getInt("Funcionario_id_funcionario"),
					rs.getInt("Paciente_id_paciente"),
					((rs.getString("consulta_realizada")).charAt(0) == 'S' ? EConsulta.CONSUTADO_SIM
							: EConsulta.CONSULTADO_NAO))
					: null;

		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.CONSULTA_DADO);
		} finally {
			bd.desconectar();
		}
	}

	public boolean altera(Agendamento agendamento) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao
					.prepareStatement("UPDATE agendamento SET id_dentista = ?,dt_agendamento = ?,"
							+ "hr_agendamento = ?, Funcionario_id_funcionario = ?,Paciente_id_paciente = ?,consulta_realizada = ?"
							+ " WHERE id_agendamento = ?;");
			pst.setInt(1, agendamento.getId_dentista());
			pst.setDate(2, new Date(agendamento.getDt_agendamento().getTime()));
			pst.setTime(3, new Time(agendamento.getHr_agendamento().getTime()));
			pst.setInt(4, agendamento.getId_funcionario());
			pst.setInt(5, agendamento.getId_paciente());
			pst.setString(6, String.valueOf(agendamento.getConsultaRealizada()
					.getSigla()));
			pst.setInt(7, agendamento.getId_agendamento());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.ATUALIZA_DADO);
		} finally {
			bd.desconectar();
		}
	}
	public boolean alteraStatus(int id_agendamento,char pagamento) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao
					.prepareStatement("UPDATE agendamento SET consulta_realizada = ?"
							+ " WHERE id_agendamento = ?;");
			pst.setString(1, String.valueOf(pagamento));
			pst.setInt(2, id_agendamento);
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.ATUALIZA_DADO);
		} finally {
			bd.desconectar();
		}
	}

	public List<Agendamento> consulta() throws BancoDadosException {
		Connection conexao = bd.conectar();
		List<Agendamento> agendamentos = new ArrayList<Agendamento>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM agendamento;");
			while (rs.next()) {
				agendamentos
						.add(new Agendamento(
								rs.getInt("id_agendamento"),
								rs.getInt("id_dentista"),
								rs.getDate("dt_agendamento"),
								rs.getTime("hr_agendamento"),
								rs.getInt("Funcionario_id_funcionario"),
								rs.getInt("Paciente_id_paciente"),
								(rs.getString("consulta_realizada")).charAt(0) == 'S' ? EConsulta.CONSUTADO_SIM
										: EConsulta.CONSULTADO_NAO));
			}
			return agendamentos;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.CONSULTA_DADO);
		} finally {
			bd.desconectar();
		}
	}

	// ***************************************************
	// ***************PAGAMENTO PACIENTE******************
	// ***************************************************
	public List<Agendamento> consultaDentista(int id_dentista)
			throws BancoDadosException {
		Connection conexao = bd.conectar();
		List<Agendamento> agendamentos = new ArrayList<Agendamento>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st
					.executeQuery("SELECT * FROM agendamento WHERE id_dentista LIKE'%"
							+ id_dentista + "%';");
			while (rs.next()) {
				agendamentos
						.add(new Agendamento(
								rs.getInt("id_agendamento"),
								rs.getInt("id_dentista"),
								rs.getDate("dt_agendamento"),
								rs.getTime("hr_agendamento"),
								rs.getInt("Funcionario_id_funcionario"),
								rs.getInt("Paciente_id_paciente"),
								(rs.getString("consulta_realizada")).charAt(0) == 'S' ? EConsulta.CONSUTADO_SIM
										: EConsulta.CONSULTADO_NAO));
			}
			return agendamentos;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.CONSULTA_DADO);
		} finally {
			bd.desconectar();
		}
	}

	// ***************************************************
	// ***************CONSULTAR AGENDAMENTO para paciente******************
	// ***************************************************
	public List<Agendamento> consultaPaciente(int paciente) throws BancoDadosException {
		Connection conexao = bd.conectar();
		List<Agendamento> agendamentos = new ArrayList<Agendamento>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st
					.executeQuery("SELECT * FROM agendamento WHERE Paciente_id_paciente LIKE'%"
							+ paciente + "%';");
			while (rs.next()) {
				agendamentos
						.add(new Agendamento(
								rs.getInt("id_agendamento"),
								rs.getInt("id_dentista"),
								rs.getDate("dt_agendamento"),
								rs.getTime("hr_agendamento"),
								rs.getInt("Funcionario_id_funcionario"),
								rs.getInt("Paciente_id_paciente"),
								(rs.getString("consulta_realizada")).charAt(0) == 'S' ? EConsulta.CONSUTADO_SIM
										: EConsulta.CONSULTADO_NAO));
			}
			return agendamentos;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.CONSULTA_DADO);
		} finally {
			bd.desconectar();
		}
	}

	public boolean insere(List<Agendamento> agendamentos)
			throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao
					.prepareStatement("INSERT INTO agendamento "
							+ "(id_dentista,dt_agendamento,hr_agendamento,Funcionario_id_funcionario,Paciente_id_paciente,consulta_realizada) "
							+ "VALUES " + "		(?, ?,?, ?,?,?);");
			for (Agendamento agendamento : agendamentos) {
				pst.setInt(1, agendamento.getId_dentista());
				pst.setDate(2, new Date(agendamento.getDt_agendamento()
						.getTime()));
				pst.setTime(3, new Time(agendamento.getHr_agendamento()
						.getTime()));
				pst.setInt(4, agendamento.getId_funcionario());
				pst.setInt(5, agendamento.getId_paciente());
				pst.setString(6, String.valueOf(agendamento.getConsultaRealizada()
						.getSigla()));
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

	public boolean exclui(int id_agendamento) throws BancoDadosException {
		Connection conexao = bd.conectar();
		try {
			PreparedStatement pst = conexao
					.prepareStatement("DELETE FROM agendamento WHERE id_agendamento = ?;");
			pst.setInt(1, id_agendamento);
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BancoDadosException(e.getMessage(),
					EBancoDadosException.EXLUI_DADO);
		} finally {
			bd.desconectar();
		}
	}
}
