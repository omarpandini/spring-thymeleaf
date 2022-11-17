package com.br.springthymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.springthymeleaf.model.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long> {
	
	Papel findByPapel(String papel);

}
