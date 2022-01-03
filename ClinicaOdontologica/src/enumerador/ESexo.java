package enumerador;


public enum ESexo {
	MASCULINO("Masculino", "Masc", 'M'), FEMININO("Feminino", "Fem", 'F'), NAO_DEFINIDO("Não Definido","N/D",'Ñ');

	private final String descricao;
	private final String abreviatura;
	private final char sigla;

	public String getDescricao() {
		return descricao;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public char getSigla() {
		return sigla;
	}

	private ESexo(String descricao, String abreviatura, char sigla) {
		this.descricao = descricao;
		this.abreviatura = abreviatura;
		this.sigla = sigla;

	}

}
