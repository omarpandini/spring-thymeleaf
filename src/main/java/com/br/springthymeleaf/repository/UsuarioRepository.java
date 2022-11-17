package com.br.springthymeleaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.springthymeleaf.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query(value = "select nome, cpf  from usuario",nativeQuery = true)
	List<String> retornaNomeCpfUsuario();

	@Query(value = "select count(*) from usuario where nome = ?1",nativeQuery = true)
	Long verificaExisteNome(String nome);
	
	Usuario findByLogin(String login);
		

}
