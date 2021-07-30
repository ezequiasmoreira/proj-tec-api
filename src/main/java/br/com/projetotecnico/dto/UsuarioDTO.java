package br.com.projetotecnico.dto;

import java.io.Serializable;

import br.com.projetotecnico.models.Usuario;

public class UsuarioDTO implements Serializable { 
	private Integer id;	
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    
    public UsuarioDTO() {}
	public UsuarioDTO(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.sobrenome = usuario.getSobrenome();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}  

}
