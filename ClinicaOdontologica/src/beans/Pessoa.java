package beans;

import enumerador.ESexo;

public abstract class Pessoa {
	private int id;
	private String nome;
	private int cpf;
	private int telefone;
	private String email;
	private ESexo sexo;
	
	public Pessoa(){
		this(0,"sem nome",00000000000,00000000,"sem email",ESexo.MASCULINO);
	}
	
	public Pessoa(int id,String nome,int cpf,int telefone,String email,ESexo sexo){
		setId(id);
		setNome(nome);
		setCpf(cpf);
		setTelefone(telefone);
		setEmail(email);
		setSexo(sexo);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ESexo getSexo() {
		return sexo;
	}
	public void setSexo(ESexo sexo) {
		this.sexo = sexo;
	}
	
	@Override
	public String toString(){
		return	
				"\n\tID: "+getId()+
				"\n\tNome: "+getNome()+
							"\n\tCpf: "+getCpf()+
							"\n\tTelefone: "+getTelefone()+
							"\n\tEmail: "+getEmail()+
							"\n\tSexo: "+getSexo().getDescricao();
	}

	
	

}
