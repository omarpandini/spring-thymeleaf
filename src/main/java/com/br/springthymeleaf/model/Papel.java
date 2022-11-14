package com.br.springthymeleaf.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Papel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String papel;
	
	@ManyToMany(mappedBy = "papeis",fetch = FetchType.EAGER)
	private List<Usuario>usuarios;	
	
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPapel() {
		return papel;
	}
	public void setPapel(String papel) {
		this.papel = papel;
	}	
	
	public Papel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Papel(String papel) {
		super();
		setPapel(papel);
	}
	
	
	
	

}
