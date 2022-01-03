package enumerador;

public enum ETipo {
	SECRETARIA("Secretária", 'S'), ADMINISTRADOR("Administrador", 'A');

	private final String descricao;
	private final char sigla;

	public String getDescricao() {
		return descricao;
	}

	public char getSigla() {
		return sigla;
	}

	private ETipo(String descricao, char sigla) {
		this.descricao = descricao;
		this.sigla = sigla;

	}

}
