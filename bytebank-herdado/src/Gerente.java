public class Gerente extends Funcionario implements Autenticavel {

	private int senha;

	public double getBonificacao() {
		// implementa��o da bonifica��o do gerente omitida
		System.out.println("Chamando bonifica��o Gerente");
		return super.getSalario();
	}

	@Override
	public void setSenha(int senha) {
		this.senha = senha;
	}

	@Override
	public boolean autentica(int senha) {

		if (this.senha == senha) {
			return true;
		} else {
			return false;
		}
	}

}