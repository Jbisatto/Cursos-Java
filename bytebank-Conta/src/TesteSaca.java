
public class TesteSaca {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Conta conta = new ContaCorrente(123, 564);

		conta.deposita(1000);
		System.out.println(conta.getSaldo());
		try {
		conta.saca(1200);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println(conta.getSaldo());

	}
}
