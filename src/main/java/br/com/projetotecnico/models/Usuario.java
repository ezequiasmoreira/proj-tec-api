package br.com.projetotecnico.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import br.com.projetotecnico.dto.UsuarioDTO;
@Entity
public class Usuario implements Serializable { 

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
    private String nome;
    private String sobrenome;
    
    @Column(unique=true)
    private String email;
    
    private String senha;
    private boolean ativo;

    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USUARIO_ROLE",
		joinColumns = @JoinColumn(name = "usuario_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id")
	)
    private List<Role> roles = new ArrayList<>();

    public Usuario() { }
    
    public Usuario(UsuarioDTO usuarioDTO) {
    	this.id = usuarioDTO.getId();
        this.nome = usuarioDTO.getNome();
        this.sobrenome = usuarioDTO.getSobrenome();
        this.email = usuarioDTO.getEmail();
        this.senha = usuarioDTO.getSenha();
    }
    
    public Usuario(Usuario usuario) {
    	this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.sobrenome = usuario.getSobrenome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.roles = usuario.getRoles();
    }

    public Usuario(Integer id, String nome, String sobrenome, String email, String senha, boolean ativo) {
    	this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;        
    }

    public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
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
   		Usuario other = (Usuario) obj;
   		if (id == null) {
   			if (other.id != null)
   				return false;
   		} else if (!id.equals(other.id))
   			return false;
   		return true;
   	}
    
}
