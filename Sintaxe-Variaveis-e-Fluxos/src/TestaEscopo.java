
public class TestaEscopo {
	public static void main(String[] args) {

		System.out.println("testando condicionais");
		int idade = 18;
		int quatidade = 3;
		boolean acompanhado = (quatidade >= 2);
		if (idade >= 18 && acompanhado) {
			System.out.println("Maior de 18");
		} else {

			System.out.println("Menor 18");
		}
	}

}
