
public class TestaCarro {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Carro carro = new Carro(2013, "Gol", 35000.0);
		Carro outroCarro = new Carro("Civic", 95000.0);
		
		System.out.println(outroCarro.getAno()+", "+outroCarro.getModelo()+", "+outroCarro.getPreco());
		System.out.println(carro.getAno()+", "+carro.getModelo()+", "+carro.getPreco());
		
		
	}

}
