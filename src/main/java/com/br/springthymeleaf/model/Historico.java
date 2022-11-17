package com.br.springthymeleaf.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "historico")
public class Historico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeHistorico;	
	private String idObrigatorio;
	
	
	
	public String getIdObrigatorio() {
		return idObrigatorio;
	}

	public void setIdObrigatorio(String idObrigatorio) {
		this.idObrigatorio = idObrigatorio;
	}
	@OneToMany(mappedBy = "idHistorico",fetch = FetchType.EAGER)
	private List<HistoricoUsuario>historicosUsuario;
	
	
	public List<HistoricoUsuario> getHistoricosUsuario() {
		return historicosUsuario;
	}

	public void setHistoricosUsuario(List<HistoricoUsuario> historicosUsuario) {
		this.historicosUsuario = historicosUsuario;
	}	


	public Historico(String nomeHistorico) {
		super();
		setNomeHistorico(nomeHistorico);
	}	

	public Historico() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeHistorico() {
		return nomeHistorico;
	}
	public void setNomeHistorico(String nomeHistorico) {
		this.nomeHistorico = nomeHistorico.toUpperCase().trim();
	}
	

}
