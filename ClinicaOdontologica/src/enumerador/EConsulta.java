package enumerador;

public enum EConsulta {
	CONSUTADO_SIM("Consultado", 'S'), CONSULTADO_NAO("Não Consultado",'N'),;

	private final String descricao;
	private final char sigla;

	public String getDescricao() {
		return descricao;
	}

	public char getSigla() {
		return sigla;
	}

	private EConsulta(String descricao, char sigla) {
		this.descricao = descricao;
		this.sigla = sigla;

	}

}