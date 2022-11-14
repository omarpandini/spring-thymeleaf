package com.br.springthymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.br.springthymeleaf.model.Papel;
import com.br.springthymeleaf.repository.PapelRepository;

@Component
public class CarregadorDados implements CommandLineRunner {
	
	@Autowired
	private PapelRepository papelRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		String[]papeis = {"ADMIN" , "USER" , "BIBLIOTECARIO"};
		
		for (String papelString : papeis) {
			
			Papel papel = papelRepository.procuraPapel(papelString);
			
			if(papel == null) {
				papel = new Papel(papelString);
				papelRepository.save(papel);
			}
			
		}
		
	}

}
