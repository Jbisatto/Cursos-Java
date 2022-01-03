
public class SubGerente extends Gerente{
	private int nomeMae;
	
	public int getNomeMae() {
		return nomeMae;
	}
	public void setNomeMae(int nomeMae) {
		this.nomeMae = nomeMae;
	}
	
	public double getBonificacao() {
		System.out.println("subgerente");
		return 600;
	}
}
