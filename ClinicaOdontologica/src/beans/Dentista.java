package beans;



import enumerador.ESexo;

public class Dentista extends Pessoa {
	private int cro;
	


	public Dentista() {
		this(0, 0, "sem nome", 0, 0, "sem email",
				ESexo.MASCULINO);
	}

	public Dentista(int id_dentista, int cro, String nome, int cpf, int telefone, String email,
			ESexo sexo) {
		super(id_dentista,nome, cpf, telefone, email, sexo);
		setCro(cro);
	}
	
		

	public int getCro() {
		return cro;
	}

	public void setCro(int cro) {
		this.cro = cro;
	}

	@Override
	public String toString() {
		return super.toString() + "\n\tCro : " + getCro()+
				"\n\t--------------------------------------------------\n";
	}

}
