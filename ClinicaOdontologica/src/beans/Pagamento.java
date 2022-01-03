package beans;


import bancoDeDados.BancoDadosException;
import dao.ItensServicoConsultaDAO;
import dao.ServicoDao;
import enumerador.EPagamento;

public class Pagamento {
	private int id_consulta;
	private String desc_consulta;
	private String nome_paciente;
	private String nome_dentista;
	private EPagamento pagamento;
	
	public EPagamento getPagamento() {
		return pagamento;
	}
	public void setPagamento(EPagamento pagamento) {
		this.pagamento = pagamento;
	}
	public int getId_consulta() {
		return id_consulta;
	}
	public void setId_consulta(int id_consulta) {
		this.id_consulta = id_consulta;
	}
	public String getDesc_consulta() {
		return desc_consulta;
	}
	public void setDesc_consulta(String desc_consulta) {
		this.desc_consulta = desc_consulta;
	}
	public String getNome_paciente() {
		return nome_paciente;
	}
	public void setNome_paciente(String nome_paciente) {
		this.nome_paciente = nome_paciente;
	}
	public String getNome_dentista() {
		return nome_dentista;
	}
	public void setNome_dentista(String nome_dentista) {
		this.nome_dentista = nome_dentista;
	}

	
	@Override
	public String toString(){
		float total=0;
		StringBuffer lista = new StringBuffer("");
		ItensServicoConsultaDAO iscDao =new ItensServicoConsultaDAO();
		Servico servico = new Servico();
		ServicoDao servicoDao = new ServicoDao();
		try {
			for (ItensServicoConsulta isc : iscDao.consulta(id_consulta)){
				servico=servicoDao.consulta(isc.getId_servico());
				total+=isc.getPreco_total();
				lista.append("\n\t\t"+servico.getNomeServico()+"\n     Qut: "+isc.getQut_servico()+"     R$"+isc.getPreco_total());
			}
		} catch (BancoDadosException e) {

			e.printStackTrace();
		}
		return  "\n\tCódigo da Consulta: "+getId_consulta()+
				"\n\tNome do Paciente: "+getNome_paciente()+
				"\n\tNome do Dentista: "+getNome_dentista()+
				"\n\tDescrição da Consulta: "+getDesc_consulta()+
				"\n\tLista de Serviços ;"+lista+
				"\n\n\tPreço Total:        R$"+total+
				"\n------------------------------------------------"
				+ "\nStatus do Pagamento: "+getPagamento().getDescricao();
	}

}
