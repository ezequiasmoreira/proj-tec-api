package br.com.projetotecnico.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.projetotecnico.dto.ClienteDTO;

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

	@Column(name = "tipo_boolean")
	private boolean tipoBoolean;

	@Column(name = "tipo_int")
	private int tipoInt ;

	@Column(name = "tipo_Double")
	private Double tipoDouble ;

	@Column(name = "tipo_doube_min")
	private double tipoDoubeMin ;

	@Column(name = "tipo_short2")
	private short tipoShort2 ;

	@Column(name = "tipo_byte2")
	private byte tipoByte2 ;

	@Column(name = "tipo_byte")
	private Byte tipoByte ;

	@Column(name = "tipo_short")
	private Short tipoShort ;

	@Column(name = "tipo_char")
	private char tipoChar;

	@Column(name = "tipo_character")
	private Character tipoCharacter;

	@Column(name = "tipo_long")
	private long tipoLong;

	@Column(name = "tipo_float")
	private Float tipoFloat;

	@Column(name = "tipo_float2")
	private float tipoFloat2;

	@Column(name = "tipo_number")
	private Number tipoNumber;


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
		this.tipoByte = 32;
		this.tipoByte2 = 32;
		this.tipoChar = 'S';
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
		this.tipoByte = 32;
		this.tipoByte2 = 32;
		this.tipoChar = 'S';
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
		this.tipoByte = 32;
		this.tipoByte2 = 32;
		this.tipoChar = 'S';
		this.tipoBoolean = false;
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

	public boolean isTipoBoolean() {
		return tipoBoolean;
	}

	public void setTipoBoolean(boolean tipoBoolean) {
		this.tipoBoolean = tipoBoolean;
	}

	public int getTipoInt() {
		return tipoInt;
	}

	public void setTipoInt(int tipoInt) {
		this.tipoInt = tipoInt;
	}

	public Double getTipoDouble() {
		return tipoDouble;
	}

	public void setTipoDouble(Double tipoDouble) {
		this.tipoDouble = tipoDouble;
	}

	public double getTipoDoubeMin() {
		return tipoDoubeMin;
	}

	public void setTipoDoubeMin(double tipoDoubeMin) {
		this.tipoDoubeMin = tipoDoubeMin;
	}

	public short getTipoShort2() {
		return tipoShort2;
	}

	public void setTipoShort2(short tipoShort2) {
		this.tipoShort2 = tipoShort2;
	}

	public byte getTipoByte2() {
		return tipoByte2;
	}

	public void setTipoByte2(byte tipoByte2) {
		this.tipoByte2 = tipoByte2;
	}

	public Byte getTipoByte() {
		return tipoByte;
	}

	public void setTipoByte(Byte tipoByte) {
		this.tipoByte = tipoByte;
	}

	public Short getTipoShort() {
		return tipoShort;
	}

	public void setTipoShort(Short tipoShort) {
		this.tipoShort = tipoShort;
	}

	public char getTipoChar() {
		return tipoChar;
	}

	public void setTipoChar(char tipoChar) {
		this.tipoChar = tipoChar;
	}

	public Character getTipoCharacter() {
		return tipoCharacter;
	}

	public void setTipoCharacter(Character tipoCharacter) {
		this.tipoCharacter = tipoCharacter;
	}

	public long getTipoLong() {
		return tipoLong;
	}

	public void setTipoLong(long tipoLong) {
		this.tipoLong = tipoLong;
	}

	public Float getTipoFloat() {
		return tipoFloat;
	}

	public void setTipoFloat(Float tipoFloat) {
		this.tipoFloat = tipoFloat;
	}

	public float getTipoFloat2() {
		return tipoFloat2;
	}

	public void setTipoFloat2(float tipoFloat2) {
		this.tipoFloat2 = tipoFloat2;
	}

	public Number getTipoNumber() {
		return tipoNumber;
	}

	public void setTipoNumber(Number tipoNumber) {
		this.tipoNumber = tipoNumber;
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
