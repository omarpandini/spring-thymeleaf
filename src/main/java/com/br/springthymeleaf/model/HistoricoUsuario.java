package com.br.springthymeleaf.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "historicos_usuario")
public class HistoricoUsuario {	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String descricaoHistorico;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Historico idHistorico;	
	
	public HistoricoUsuario() {
		super();
	}
	
	@ManyToOne
	@LazyCollection(LazyCollectionOption.FALSE)
	private Usuario usuario;	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public HistoricoUsuario(String descricaoHistorico, Historico idHistorico,Usuario usuario) {
		super();
		this.descricaoHistorico = descricaoHistorico;
		this.idHistorico = idHistorico;
		this.usuario = usuario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Historico getIdHistorico() {
		return idHistorico;
	}

	public void setIdHistorico(Historico idHistorico) {
		this.idHistorico = idHistorico;
	}

	public String getDescricaoHistorico() {
		return descricaoHistorico;
	}

	public void setDescricaoHistorico(String descricaoHistorico) {
		this.descricaoHistorico = descricaoHistorico;
	}	
	

}
