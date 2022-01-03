package beans;

import enumerador.EPagamento;



public class Consulta {
	private int id_consulta;
	private int id_agendamento;	
	private String descricaoConsulta;
	private EPagamento pagamento;
	

	
	public Consulta(int id_consulta,int id_agendamento,String descricaoConsulta,EPagamento pagamento){
		setId_consulta(id_consulta);		
		setId_agendamento(id_agendamento);
		setDescricaoConsulta(descricaoConsulta);
		setPagamento(pagamento);		
	}
	
	public Consulta(){
		this(0,0,"Não Cadastrado",EPagamento.DEVE);
	}
	
	
	public EPagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(EPagamento pagamento) {
		this.pagamento = pagamento;
	}


	public int getId_agendamento() {
		return id_agendamento;
	}

	public void setId_agendamento(int id_agendamento) {
		this.id_agendamento = id_agendamento;
	}

	public int getId_consulta() {
		return id_consulta;
	}

	public void setId_consulta(int id_consulta) {
		this.id_consulta = id_consulta;
	}
	

	public String getDescricaoConsulta() {
		return descricaoConsulta;
	}
	public void setDescricaoConsulta(String descricaoConsulta) {
		this.descricaoConsulta = descricaoConsulta;
	}
	
	@Override
	public String toString(){
		return getClass().getSimpleName()+"\n\tNumero da Consulta: "+getId_consulta()+
				"\n\tNumero do Agendamento: "+getId_agendamento()+
				"\n\tDescrição da Consulta: "+getDescricaoConsulta()+
				"\n\tStatus Pagamento: "+getPagamento().getDescricao()+
				"\n\t--------------------------------------------------\n";
	}


	
}
