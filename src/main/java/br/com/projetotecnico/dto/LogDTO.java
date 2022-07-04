package br.com.projetotecnico.dto;

import java.io.Serializable;

public class LogDTO implements Serializable {
	private Integer id;

    public LogDTO() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


}
