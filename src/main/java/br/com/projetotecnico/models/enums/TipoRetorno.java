package br.com.projetotecnico.models.enums;

public enum TipoRetorno {
	INTEGER(1, "class java.lang.Integer"),
	LONG(2,"class java.lang.Long"),
	FLOAT(3,"class java.lang.Float"),
	NUMBER(4,"class java.lang.Number"),
	DOUBLE(5,"class java.lang.Double"),
	SHORT(6, "class java.lang.Short"),
	CHARACTER(7, "class java.lang.Character"),
	BYTE(8, "class java.lang.Byte"),
	STRING(9, "class java.lang.String"),
	LIST(10,"java.util.List"),
	SET(11, "java.util.Set"),
	DATE(12, "class java.util.Date"),
	LOCAL_DATE(13,"class java.time.LocalDate"),
	LOCAL_DATE_TIME(14,"class java.time.LocalDateTime"),
	LOCAL_TIME(15,"class java.time.LocalTime"),
	BIG_DECIMAL(16,"class java.math.BigDecimal"),
	BOOLEAN(17,"class java.lang.Boolean"),
	LIST_OBJET(18, "lista de objeto"),
	SET_OBJET(19, "set de objeto"),
	OBJET(20, "objeto"),
	VOID(21, "void"),
	INT_PRIMITIVO(22, "int"),
	FLOAT_PRIMITIVO(23, "float"),
	LONG_PRIMITIVO(24,"long"),
	SHORT_PRIMITIVO(25,"short"),
	BYTE_PRIMITIVO(26,"byte"),
	CHAR_PRIMITIVO(27,"char"),
	DOUBLE_PRIMITIVO(29,"double"),
	BOOLEAN_PRIMITIVO(30,"boolean");

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