package br.com.projetotecnico.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

import br.com.projetotecnico.models.enums.AcaoEntity;

@Entity
public class Log implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(length=10000)
	private String campos;
	
	private String entity;
	
	private String identificador;
	
	private Integer acaoEntity;
	
	private Date dataCadastro;

	@OneToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;

	public Log() {}

	public Log(Integer id, String campos, String entity, String identificador, AcaoEntity acaoEntity, Date dataCadastro,
			Usuario usuario) {
		super();
		this.id = id;
		this.campos = campos;
		this.entity = entity;
		this.identificador = identificador;
		this.acaoEntity = acaoEntity.getCod();
		this.dataCadastro = dataCadastro;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCampos() {
		return campos;
	}

	public void setCampos(String campos) {
		this.campos = campos;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Integer getAcaoEntity() {
		return acaoEntity;
	}

	public void setAcaoEntity(AcaoEntity acaoEntity) {
		this.acaoEntity = acaoEntity.getCod();
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Log other = (Log) obj;
		return Objects.equals(id, other.id);
	}	
}