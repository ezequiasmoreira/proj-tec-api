package br.com.projetotecnico.dto;

import java.io.Serializable;

import br.com.projetotecnico.models.Telefone;
import br.com.projetotecnico.models.Usuario;

public class TelefoneDTO implements Serializable { 
	private Integer id;	
    private String descricao;
    private Integer tipo;
    private String numero;
    
    public TelefoneDTO() {}

    public TelefoneDTO(Telefone telefone) {
		super();
		this.id = telefone.getId();
		this.descricao = telefone.getDescricao();
		this.tipo = telefone.getTipo();
		this.numero = telefone.getNumero();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}
