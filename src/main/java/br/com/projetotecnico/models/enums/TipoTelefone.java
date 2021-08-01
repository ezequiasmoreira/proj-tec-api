package br.com.projetotecnico.models.enums;

public enum TipoTelefone {
	RESIDENCIAL(1, "Residencial"),
	COMERCIAL(2, "Comercial"),
	CELULAR(3, "Celular");
	
	private int cod;
	private String descricao;
	
	private TipoTelefone(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoTelefone toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		for(TipoTelefone x : TipoTelefone.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}		
		}
		throw new IllegalArgumentException("Id inválido " + cod);
	}
}