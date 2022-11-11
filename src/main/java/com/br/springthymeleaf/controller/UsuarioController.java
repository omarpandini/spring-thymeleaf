package com.br.springthymeleaf.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.springthymeleaf.model.Usuario;
import com.br.springthymeleaf.repository.UsuarioRepository;


@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping(value = "/novo")
	public String adicionarUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "publica-criar-usuario";
	}
	
	@PostMapping(value = "/salvar")
	public String salvarUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {

		if(result.hasErrors()) {
			return "/publica-criar-usuario";	
		}
		
		usuarioRepository.save(usuario);
		attributes.addFlashAttribute("mensagem","Usuário salvo com sucesso");
		return "redirect:/usuario/novo";
	}
	
	@GetMapping(value = "/admin/listar")
	public String retornaUsuarios(Model model) {

		List<Usuario> usuarios = usuarioRepository.findAll(Sort.by(Sort.Direction.ASC,"nome"));
		
	    model.addAttribute("usuarios", usuarios);
		
		return "/auth/admin/admin-listar-usuario";
	}
	
	@GetMapping(value = "/admin/apagar/{id}")
	public String apagar(@PathVariable("id") long id) {
		System.out.println("Apagando Usuário "+ id);
		usuarioRepository.deleteById(id);
		return "redirect:/usuario/admin/listar";
	}
	
	
	@RequestMapping(value = "/testeNome/{nome}",method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String retornaUsuarioExiste(@PathVariable String nome) {
		Long num = usuarioRepository.verificaExisteNome(nome.toUpperCase().trim());
		return "Olá "+nome+" achou:  "+num;
	}
	
	@RequestMapping(value = "editar/{id}")
	public String alterarUsuario(@PathVariable Long id, Model model) {
		
		Usuario usuario = usuarioRepository.findById(id).get();
		
		model.addAttribute("usuario", usuario);
		
		return "auth/user/user-alterar-usuario";
	}
	
	
	@PostMapping(value = "/alterar" )
	public String alterarInfoUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			return "auth/user/user-alterar-usuario";
		}		
		
		Usuario novoUsuario = usuarioRepository.saveAndFlush(usuario);		
		
		attr.addAttribute("id", novoUsuario.getId()).addFlashAttribute("mensagemAlterar", "Usuário atualizado com sucesso");
		
		return "redirect:/usuario/editar/{id}";
	}

}
