package enumerador;

public enum EPagamento {
	PAGO("Pago", 'P'), DEVE("Deve",'D'), ABERTO("Aberto",'A');

	private final String descricao;
	private final char sigla;

	public String getDescricao() {
		return descricao;
	}

	public char getSigla() {
		return sigla;
	}

	private EPagamento(String descricao, char sigla) {
		this.descricao = descricao;
		this.sigla = sigla;

	}

}
