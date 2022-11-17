package com.br.springthymeleaf.model;

import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 3, message = "O nome deve ter no mínimo 3 caracateres")
	private String nome;	
	
	
	@CPF(message = "CPF inválido")
	private String cpf;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Data de Nascimento obrigatória")
	private Date dataNascimento;
	
	@Email(message = "Email inválido")
	@NotEmpty(message = "Email obrigatório")
	private String email;

	@NotEmpty(message = "Senha obrigatória")
	@Size(min = 5, message = "Erro: deve ter no mínimo 5 caracteres")
	private String password;

	@NotEmpty(message = "Login obrigatório")
	private String login;
	
	@Column(nullable = false , columnDefinition = "BOOLEAN DEFAULT FALSE")
	private boolean ativo;	
	
	private Date criadoEm;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_papel",
			   joinColumns = @JoinColumn(name="usuario_id"),
			   inverseJoinColumns = @JoinColumn(name="papel_id")
			   
			)
	private List<Papel>papeis;	
	
	
	@OneToMany(mappedBy = "usuario")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<HistoricoUsuario>historicosUsuario;	

	public List<HistoricoUsuario> getHistoricosUsuario() {
		return historicosUsuario;
	}

	public void setHistoricosUsuario(List<HistoricoUsuario> historicosUsuario) {
		this.historicosUsuario = historicosUsuario;
	}

	public List<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}

	public Date getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
	}

	public Usuario() {
		super();
		this.ativo = true;
		this.criadoEm = new Date();
	}
	
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome.toUpperCase().trim();
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	
	
	

}
