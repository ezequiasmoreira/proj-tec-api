package br.com.projetotecnico.models.enums;

public enum AcaoEntity {
	CRIAR(1, "Criar"),
	ATUALIZAR(2, "Atualizar"),
	DELETE(3, "Delete");
	
	private int cod;
	private String descricao;
	
	private AcaoEntity(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public static AcaoEntity toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		for(AcaoEntity x : AcaoEntity.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}		
		}
		throw new IllegalArgumentException("Id inválido " + cod);
	}
}