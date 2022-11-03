package br.com.projetotecnico.dto;

import java.io.Serializable;

public class LogDTO implements Serializable {
	private Integer id;

	private String classe;

	private Boolean object = Boolean.TRUE;

	private String tipo;

	private String nome;

    public LogDTO() {}

	public LogDTO(String classe, String nome) {
		this.classe = classe;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public Boolean getObject() {
		return object;
	}

	public void setObject(Boolean object) {
		this.object = object;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
