package br.com.projetotecnico.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.projetotecnico.models.Cliente;
import br.com.projetotecnico.models.Endereco;
import br.com.projetotecnico.models.Telefone;
import br.com.projetotecnico.models.Usuario;

public class ClienteDTO implements Serializable { 
	private Integer id;	
    private String nome;
    private String sobrenome;
    private String cpfCnpj;
    private List<Telefone> telefones = new ArrayList<>();
    private List<Endereco> enderecos = new ArrayList<>();
    
    public ClienteDTO() {}

    public ClienteDTO(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.sobrenome = cliente.getSobrenome();
		this.cpfCnpj = cliente.getCpfCnpj();
		this.telefones = cliente.getTelefones();
		this.enderecos = cliente.getEnderecos();
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
}
