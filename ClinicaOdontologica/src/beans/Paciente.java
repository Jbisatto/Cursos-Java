package beans;



import java.text.SimpleDateFormat;
import java.util.Date;

import enumerador.ESexo;

public class Paciente extends Pessoa {
	private String convenio;
	private Date dt_nascimento;

	
	public Paciente() {
		this(0, "sem convênio", new Date(), "sem nome", 00000000000, 00000000,
				"sem email", ESexo.MASCULINO);
	}

	public Paciente(int id_paciente, String convenio, Date dt_nascimento, String nome,
			int cpf, int telefone, String email, ESexo sexo) {
		super(id_paciente,nome, cpf, telefone, email, sexo);
		setConvenio(convenio);
		setDt_nascimento(dt_nascimento);
	}

	public String getConvenio() {
		return convenio;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

	public Date getDt_nascimento() {
		return dt_nascimento;
	}

	public void setDt_nascimento(Date dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return super.toString() + "\n\tConvenio: " + getConvenio()
				+ "\n\tData de nascimento: " + sdf.format(getDt_nascimento())+
				"\n\t--------------------------------------------------\n";
	}
}
