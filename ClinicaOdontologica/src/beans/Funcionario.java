package beans;



import enumerador.ESexo;
import enumerador.ETipo;

public class Funcionario extends Pessoa {
	private ETipo tipo;

	public Funcionario() {
		this(0,ETipo.SECRETARIA, "sem nome", 00000000000, 00000000, "sem email",
				ESexo.MASCULINO);
	}

	public Funcionario(int id_funcionario,ETipo tipo, String nome, int cpf, int telefone,
			String email, ESexo sexo) {
		super(id_funcionario,nome, cpf, telefone, email, sexo);
		setTipo(tipo);
	}

	public Funcionario(int id, String string, ETipo eTipo) {
	}

	public void setTipo(ETipo tipo) {
		this.tipo = tipo;
	}

	public ETipo getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return super.toString() + "\n\tTipo : " + getTipo().getDescricao()+
				"\n\t--------------------------------------------------\n";
	}

}
