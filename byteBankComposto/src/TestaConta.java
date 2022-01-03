
public class TestaConta {

	public static void main(String[] args) {
		Conta contaMarcela = new Conta();
		Cliente cliente = new Cliente();
		cliente.setCpf("010.108.709/80");
		
		contaMarcela.setTitular(cliente);
		System.out.println(contaMarcela.getTitular().getCpf());
		cliente.setNome("Marcela");
		System.out.println(contaMarcela.getTitular().getNome());
		
		contaMarcela.deposita(200);
		contaMarcela.saca(30);
		System.out.println(contaMarcela.getTitular());
		
		System.out.println(contaMarcela.getSaldo());

	}

}
