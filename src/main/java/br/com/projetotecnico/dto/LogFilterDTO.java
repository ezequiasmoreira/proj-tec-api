package br.com.projetotecnico.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogFilterDTO implements Serializable {
	private Integer identificador;

	private String classe;

	private LocalDateTime dataInicial;

	private LocalDateTime dataFinal;

	private String campoName;

	private String campoValue;

	private List<Integer> acao = new ArrayList<>();

    public LogFilterDTO() {}

	public Integer getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public LocalDateTime getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDateTime dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDateTime getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDateTime dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getCampoName() {
		return campoName;
	}

	public void setCampoName(String campoName) {
		this.campoName = campoName;
	}

	public String getCampoValue() {
		return campoValue;
	}

	public void setCampoValue(String campoValue) {
		this.campoValue = campoValue;
	}

	public List<Integer> getAcao() {
		return acao;
	}

	public void setAcao(List<Integer> acao) {
		this.acao = acao;
	}
}
