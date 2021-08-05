package br.com.projetotecnico.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.projetotecnico.dto.ClienteDTO;
import br.com.projetotecnico.dto.UsuarioDTO;

@Entity
public class Cliente implements Serializable { 

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
    private String nome;
    private String sobrenome;
    
    @Column(unique=true)
    private String cpfCnpj;
    
    @OneToOne
	@JoinColumn(name="endereco_id")
    private Endereco endereco;
    
    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinTable(name = "CLIENTE_TELEFONE",
		joinColumns = @JoinColumn(name = "cliente_id"),
		inverseJoinColumns = @JoinColumn(name = "telefone_id")
	)
    private List<Telefone> telefones = new ArrayList<>();
    
    @OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "CLIENTE_ENDERECO",
		joinColumns = @JoinColumn(name = "cliente_id"),
		inverseJoinColumns = @JoinColumn(name = "endereco_id")
	)
    private List<Endereco> enderecos = new ArrayList<>();

    public Cliente() { }

    public Cliente(ClienteDTO clienteDTO) {
    	this.id = clienteDTO.getId();
        this.nome = clienteDTO.getNome();
        this.sobrenome = clienteDTO.getSobrenome();
        this.cpfCnpj = clienteDTO.getCpfCnpj();
    }
    
	public Cliente(Integer id, String nome, String sobrenome, String cpfCnpj) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpfCnpj = cpfCnpj;
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

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
