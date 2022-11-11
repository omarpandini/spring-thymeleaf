package com.br.springthymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("msnBemVindo","Bem vindo a Biblioteca BooksOnTheTable");
		return "publica-index";
	}

}
