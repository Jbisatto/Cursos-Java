package bancoDeDados;


public class BancoDadosException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public BancoDadosException(String mensagem, EBancoDadosException excecao) {
		super(excecao.getMensagem() + "#" + mensagem);
	}

}
