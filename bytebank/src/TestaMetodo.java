
public class TestaMetodo {

	public static void main(String[] args) {
		Conta conta = new Conta();
		conta.saldo = 100;
		conta.deposita(conta.saldo);
		System.out.println(conta.saldo);
		conta.deposita(50);
		System.out.println(conta.saldo);

		if (conta.saca(300)) {
			System.out.println("Valor sacado com sucesso");
		} else {
			System.out.println("Voc� n�o tem saldo suficiente");
		}
		System.out.println(conta.saldo);
		
		Conta conta2= new Conta();
		conta2.saldo=1000;
		if (conta.transfere(100,conta2)) {
			System.out.println("Valor sacado com sucesso");
		} else {
			System.out.println("Voc� n�o tem saldo suficiente");
		}
		System.out.println("Saldo da conta � "+conta.saldo);
		System.out.println("Saldo da conta 2 � "+conta2.saldo);
		
		
		
	}

}
