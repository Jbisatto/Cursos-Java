package beans;


import java.text.SimpleDateFormat;
import java.util.Date;

import enumerador.EConsulta;



public class Agendamento {
	private int id_agendamento;
	private int id_dentista;
	private Date dt_agendamento;
	private Date hr_agendamento;
	private int id_funcionario;
	private int id_paciente;
	private EConsulta consultaRealizada;


	public Agendamento(){
		this(0,0,new Date(),new Date(),0,0,EConsulta.CONSULTADO_NAO);
	}
	public Agendamento(int id_agendamento,int id_dentista, Date dt_agendamento,
			Date hr_agendamento, int id_funcionario, int id_paciente,EConsulta status) {
		setId_agendamento(id_agendamento);
		setId_dentista(id_dentista);
		setDt_agendamento(dt_agendamento);
		setHr_agendamento(hr_agendamento);
		setId_funcionario(id_funcionario);
		setId_paciente(id_paciente);
		setConsultaRealizada(status);

	}
	
	public EConsulta getConsultaRealizada() {
		return consultaRealizada;
	}
	public void setConsultaRealizada(EConsulta consultaRealizada) {
		this.consultaRealizada = consultaRealizada;
	}
	
	public int getId_dentista() {
		return id_dentista;
	}

	public void setId_dentista(int id_dentista) {
		this.id_dentista = id_dentista;
	}

	public Date getDt_agendamento() {
		return dt_agendamento;
	}

	public void setDt_agendamento(Date dt_agendamento) {
		this.dt_agendamento = dt_agendamento;
	}

	public Date getHr_agendamento() {
		return hr_agendamento;
	}

	public void setHr_agendamento(Date hr_agendamento) {
		this.hr_agendamento = hr_agendamento;
	}

	public int getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(int id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public int getId_paciente() {
		return id_paciente;
	}

	public void setId_paciente(int id_paciente) {
		this.id_paciente = id_paciente;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdfhora = new SimpleDateFormat("HH:mm");
		return 	"\n\tCódigo do Agendamento : " + getId_agendamento()
				+"\n\tCódigo do Dentista : " + getId_dentista()
				+ "\n\tData do Agendamento : " + sdf.format(getDt_agendamento())
				+ "\n\tHora do Agendamento : " + sdfhora.format(getHr_agendamento())
				+ "\n\tCódigo do Funcionário :" + getId_funcionario()
				+ "\n\tCódigo do Paciente :" + getId_paciente()
				+ "\n\tConsulta Realizada :"+getConsultaRealizada().getDescricao()
				+ "\n\t--------------------------------------------------\n";
	}
	public int getId_agendamento() {
		return id_agendamento;
	}
	public void setId_agendamento(int id_agendamento) {
		this.id_agendamento = id_agendamento;
	}

}
