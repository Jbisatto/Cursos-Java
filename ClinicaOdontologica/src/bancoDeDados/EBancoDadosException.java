package bancoDeDados;

public enum EBancoDadosException {
	ABRE_CONEXAO ("Erro ao abrir conexão com o banco de dados"),
	FECHA_CONEXAO ("Erro ao fechar a conexão com o banco de dados"),
	CRIA_TABELA ("Erro ao criar a tabela especificada"),
	EXCLUI_TABELA ("Erro ao excluir a tabela especificada"),
	INSERE_DADO ("Erro ao inserir dados na tabela especificada"),
	CONSULTA_DADO ("Erro ao consultar dados na tabela especificada"),
	ATUALIZA_DADO ("Erro ao atualizar dados na tabela especificada"),
	EXLUI_DADO ("Erro ao excluir dados da tabela especificada"),
	ROLLBACK ("Erro ao efetuar ROLLBACK na tabela especificada");
	
	private final String mensagem;

	public String getMensagem() {
		return mensagem;
	}
	
	private EBancoDadosException(String mensagem) {
		this.mensagem = mensagem;
	}
}
