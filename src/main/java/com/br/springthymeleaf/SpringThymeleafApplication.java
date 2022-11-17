package com.br.springthymeleaf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.springthymeleaf.controller.UsuarioController;
import com.br.springthymeleaf.model.Usuario;
import com.br.springthymeleaf.repository.UsuarioRepository;

@SpringBootApplication
public class SpringThymeleafApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringThymeleafApplication.class, args);	
			
		
	
		
	}

}
