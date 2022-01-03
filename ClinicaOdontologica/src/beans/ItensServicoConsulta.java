package beans;

public class ItensServicoConsulta {
	private int id_consulta;
	private int id_servico;
	private int qut_servico;
	private float preco_total;

	
	
	public ItensServicoConsulta(){
		this(0,0,0,0);
	}
	
	
	public ItensServicoConsulta(int id_consulta, int id_servico,int qut_servico, float preco_total) {
		setId_consulta(id_consulta);
		setId_servico(id_servico);
		setPreco_total(preco_total);
		setQut_servico(qut_servico);
	}

	public int getId_consulta() {
		return id_consulta;
	}

	public void setId_consulta(int id_consulta) {
		this.id_consulta = id_consulta;
	}

	public int getId_servico() {
		return id_servico;
	}

	public void setId_servico(int id_servico) {
		this.id_servico = id_servico;
	}

	public int getQut_servico() {
		return qut_servico;
	}

	public void setQut_servico(int qut_servico) {
		this.qut_servico = qut_servico;
	}

	public float getPreco_total() {
		return preco_total;
	}

	public void setPreco_total(float preco_total) {
		this.preco_total = preco_total;
	}
	
	@Override
	public String toString(){
		return "\n\tCódigo da Consulta : "+getId_consulta()
				+ "\n\tCódigo do Serviço : "+getId_servico()
				+ "\n\tQuantidade de Serviços : "+getQut_servico()+
				"\n\tPreço Total : "+getPreco_total()+
				"\n\t--------------------------------------------------\n";
	}

}
