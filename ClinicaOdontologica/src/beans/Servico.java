package beans;

public class Servico {
	private int id_servico;
	private String nomeServico;
	private float precoServico;

	public Servico() {
		this(0, "sem nome", 0);
	}

	public Servico(int id_servico, String nomeServico, float preco) {
		setId_servico(id_servico);
		setNomeServico(nomeServico);
		setPrecoServico(preco);

	}

	public int getId_servico() {
		return id_servico;
	}

	public void setId_servico(int id_servico) {
		this.id_servico = id_servico;
	}

	public String getNomeServico() {
		return nomeServico;
	}

	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}

	public float getPrecoServico() {
		return precoServico;
	}

	public void setPrecoServico(float precoServico) {
		this.precoServico = precoServico;
	}

	@Override
	public String toString() {
		return "\nCódigo do Serviço: "+getId_servico()+"\nNome do Serviço : " + getNomeServico() + "\nPreço : "
				+ getPrecoServico()+
				"\n--------------------------------------------------\n";
	}

}
