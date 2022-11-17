package com.br.springthymeleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.springthymeleaf.model.Historico;
import com.br.springthymeleaf.repository.HistoricoRepository;

@Controller
@RequestMapping(value = "/historico")
public class HistoricoController {
	
	@Autowired
	HistoricoRepository repo;
	
	@RequestMapping(value = "listar")
	public String retornaListaHistorico(Model model) {
		
		List<Historico>historicos = repo.findAll(Sort.by(Sort.Direction.ASC,"nomeHistorico"));;
		
		model.addAttribute("historicos", historicos);
		
		return "/auth/admin/admin-listar-historico";
	}
	
	@PostMapping(value = "/salvar")
	public String salvar(@RequestParam String nomeHistorico, RedirectAttributes attr) {
		System.out.println("Salvando histórico: "+ nomeHistorico);
		
		if(nomeHistorico.trim().isEmpty()) {
			attr.addFlashAttribute("mensagemErro", "Erro: Campo obrigatório");
			return "redirect:/historico/listar";
		}
		
		Historico hist = repo.findByNomeHistorico(nomeHistorico.toUpperCase().trim());
		
		if(hist == null) {		
			hist = new Historico(nomeHistorico);
			repo.save(hist);
			attr.addFlashAttribute("mensagem", "Histórico inserido com sucesso!");
		}else {
			attr.addFlashAttribute("mensagemErro", "Erro: histórico já existe");
		}
		return "redirect:/historico/listar";
	}
	
	
	@GetMapping(value = "/apagar/{id}")
	public String deletar(@PathVariable(name = "id") Long id) {
		repo.deleteById(id);
		return "redirect:/historico/listar";
	}
	
	
	@GetMapping(value = "/editar/{id}")
	public String editar(@PathVariable(name = "id") Long id, Model model) {
		Historico hist = repo.findById(id).get();
		
		hist.setId(id);
		model.addAttribute("historico", hist);
		
		return "/auth/admin/admin-alterar-historico";
	}
	
	@PostMapping(value = "/editar")
	public String editarSalvar(Historico historico) {
		repo.saveAndFlush(historico);
		return "redirect:/historico/listar";
	}

}
