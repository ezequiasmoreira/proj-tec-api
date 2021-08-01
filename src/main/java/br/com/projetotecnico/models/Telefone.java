package br.com.projetotecnico.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.projetotecnico.dto.TelefoneDTO;
import br.com.projetotecnico.dto.UsuarioDTO;
import br.com.projetotecnico.models.enums.TipoTelefone;
@Entity
public class Telefone implements Serializable { 

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
    private String descricao;
    private Integer tipo;
    private String numero;
    
    public Telefone() { }
    
    public Telefone(TelefoneDTO telefoneDTO ) {
    	this.id = telefoneDTO.getId();
        this.descricao = telefoneDTO.getDescricao();
        this.tipo = telefoneDTO.getTipo();
        this.numero = telefoneDTO.getNumero();
    }

	public Telefone(Integer id, String descricao, TipoTelefone tipoTelefone, String numero) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.tipo = tipoTelefone.getCod();
		this.numero = numero;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefone other = (Telefone) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
    
}
