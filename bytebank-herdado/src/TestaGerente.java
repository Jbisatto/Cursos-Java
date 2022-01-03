
public class TestaGerente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Gerente g1 = new Gerente();
		g1.setNome("Joaquim");
		g1.setSalario(5000);
		g1.setSenha(1010);
		System.out.println(g1.getBonificacao());
		System.out.println(g1.getNome());

		ControleBonificacao controle = new ControleBonificacao();
		controle.registra(g1);
		System.out.println(g1.getBonificacao());
		System.out.println(controle.getSoma());
		Gerente g2 = new Gerente();
		g2.setSalario(3000);
		controle.registra(g2);
		System.out.println(g2.getBonificacao());
		System.out.println(controle.getSoma());

	}

}
