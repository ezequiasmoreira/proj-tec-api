package br.com.projetotecnico.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

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

import br.com.projetotecnico.models.enums.AcaoEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    
    @JsonIgnore
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

	@Column(name = "data_cadastro")
	private Date dataCadastro;

	@Column(name = "valor_estimado")
	private BigDecimal valorEstimado;

	@Column(name = "codigo")
	private Long codigo;

	@Column(name = "data_suspensao")
	private LocalDate dataSuspensao;

	@Column(name = "data_alteracao")
	private LocalDateTime dataAlteracao;

	@Column(name = "hora_alteracao")
	private LocalTime horaAlteracao;

	private Boolean status = true;

	@OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinTable(name = "CLIENTE_TELEFONE_SET",
			joinColumns = @JoinColumn(name = "cliente_id"),
			inverseJoinColumns = @JoinColumn(name = "telefone_id")
	)
	private Set<Telefone> contatos = new HashSet<>();


	public Cliente() {
		this.dataCadastro = new Date();
		this.dataSuspensao = getLocalDate();
		this.dataAlteracao = LocalDateTime.now();
		this.horaAlteracao = LocalTime.now();
		this.valorEstimado = this.valorEstimado == null ? new BigDecimal(getNumeroAleatorio()) :  this.valorEstimado ;
		this.codigo = this.codigo == null ? Long.valueOf(getNumeroAleatorio()) :  this.codigo ;
	}

    public Cliente(ClienteDTO clienteDTO) {
    	this.id = clienteDTO.getId();
        this.nome = clienteDTO.getNome();
        this.sobrenome = clienteDTO.getSobrenome();
        this.cpfCnpj = clienteDTO.getCpfCnpj();
		this.dataCadastro = new Date();
		this.dataSuspensao = getLocalDate();
		this.dataAlteracao = LocalDateTime.now();
		this.horaAlteracao = LocalTime.now();
		this.valorEstimado = this.valorEstimado == null ? new BigDecimal(getNumeroAleatorio()) :  this.valorEstimado ;
		this.codigo = this.codigo == null ? Long.valueOf(getNumeroAleatorio()) :  this.codigo ;
	}
    
	public Cliente(Integer id, String nome, String sobrenome, String cpfCnpj) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpfCnpj = cpfCnpj;
		this.dataCadastro = new Date();
		this.dataSuspensao = getLocalDate();
		this.horaAlteracao = LocalTime.now();
		this.dataAlteracao = LocalDateTime.now();
		this.valorEstimado = this.valorEstimado == null ? new BigDecimal(getNumeroAleatorio()) :  this.valorEstimado ;
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
		Set<Telefone> ts = new HashSet<>();
		for (Telefone t: telefones) {
			ts.add(t);
		}
		setContatos(ts);
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public BigDecimal getValorEstimado() {
		return valorEstimado;
	}

	public void setValorEstimado(BigDecimal valorEstimado) {
		this.valorEstimado = valorEstimado;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public LocalDate getDataSuspensao() {
		return dataSuspensao;
	}

	public void setDataSuspensao(LocalDate dataSuspensao) {
		this.dataSuspensao = dataSuspensao;
	}

	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public  LocalDate getLocalDate(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return LocalDate.of(calendar.get(1), calendar.get(2) + 1, calendar.get(5));
	}

	public Integer getNumeroAleatorio(){
		Random gerador = new Random();
		return gerador.nextInt();
	}

	public LocalTime getHoraAlteracao() {
		return horaAlteracao;
	}

	public void setHoraAlteracao(LocalTime horaAlteracao) {
		this.horaAlteracao = horaAlteracao;
	}

	public Set<Telefone> getContatos() {
		return contatos;
	}

	public void setContatos(Set<Telefone> contatos) {
		this.contatos = contatos;
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
