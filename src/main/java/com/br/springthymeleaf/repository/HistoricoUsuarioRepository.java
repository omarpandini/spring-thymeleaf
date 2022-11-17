package com.br.springthymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.springthymeleaf.model.HistoricoUsuario;

public interface HistoricoUsuarioRepository extends JpaRepository<HistoricoUsuario, Long> {

}
