package br.com.projetotecnico.dto;

import java.io.Serializable;

public class LogDTO implements Serializable {
	private Integer id;

	private String classe;

	private Boolean object = true;

    public LogDTO() {}

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
}
