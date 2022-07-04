package br.com.projetotecnico.models.enums;

public enum TipoRetorno {
	INTEGER(1, "java.lang.Integer"),
	STRING(2, "java.lang.String"),
	LIST(4,"java.util.List"),
	LIST_OBJET(5, "lista de objeto"),
	OBJET(6, "objeto"),
	VOID(7, "void");

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