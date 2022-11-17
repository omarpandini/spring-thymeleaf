package com.br.springthymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.springthymeleaf.model.Historico;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
	
	Historico findByNomeHistorico(String nomeHistorico);

}
