package br.com.projetotecnico.models.enums;

public enum TipoRetorno {
	INTEGER(1, "java.lang.Integer"),
	LONG(2,"java.lang.Long"),
	STRING(3, "java.lang.String"),
	LIST(4,"java.util.List"),
	SET(5, "java.util.Set"),
	DATE(6, "java.util.Date"),
	LOCAL_DATE(7,"java.time.LocalDate"),
	LOCAL_DATE_TIME(8,"java.time.LocalDateTime"),
	LOCAL_TIME(9,"java.time.LocalTime"),
	BIG_DECIMAL(10,"java.math.BigDecimal"),
	BOOLEAN(11,"java.lang.Boolean"),
	LIST_OBJET(12, "lista de objeto"),
	SET_OBJET(13, "set de objeto"),
	OBJET(14, "objeto"),
	VOID(15, "void");

	private int cod;
	private String descricao;

	private TipoRetorno(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoRetorno toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		for(TipoRetorno x : TipoRetorno.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}		
		}
		throw new IllegalArgumentException("Id inválido " + cod);
	}
}