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
import javax.persistence.OneToMany;

@Entity
public class Cliente implements Serializable { 

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
    private String nome;
    private String sobrenome;
    private String cpfCnpj;
    private Telefone telefonePrincipal;

    @OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "CLIENTE_TELEFONE",
		joinColumns = @JoinColumn(name = "cliente_id"),
		inverseJoinColumns = @JoinColumn(name = "telefone_id")
	)
    private List<Telefone> telefones = new ArrayList<>();

    public Cliente() { }

	public Cliente(Integer id, String nome, String sobrenome, String cpfCnpj, Telefone telefonePrincipal,
			List<Telefone> telefones) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpfCnpj = cpfCnpj;
		this.telefonePrincipal = telefonePrincipal;
		this.telefones = telefones;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Telefone getTelefonePrincipal() {
		return telefonePrincipal;
	}

	public void setTelefonePrincipal(Telefone telefonePrincipal) {
		this.telefonePrincipal = telefonePrincipal;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}   
}
