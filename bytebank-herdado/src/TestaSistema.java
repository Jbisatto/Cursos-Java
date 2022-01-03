
public class TestaSistema {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gerente g = new Gerente();
		g.setSenha(222);

		SistemaInterno si = new SistemaInterno();

		si.autentica(g);
	}

}
