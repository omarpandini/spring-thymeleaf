package com.br.springthymeleaf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.br.springthymeleaf.model.Papel;
import com.br.springthymeleaf.model.Usuario;
import com.br.springthymeleaf.repository.PapelRepository;
import com.br.springthymeleaf.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;


@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	PapelRepository papelRepository;
	
	@GetMapping(value = "/novo")
	public String adicionarUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "publica-criar-usuario";
	}
	
	@PostMapping(value = "/salvar")
	public String salvarUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes, Model model) {

		if(result.hasErrors()) {
			return "/publica-criar-usuario";	
		}
		
		//Valida se login já existe
		Usuario login = usuarioRepository.findByLogin(usuario.getLogin());
		
		if(login != null) {
			model.addAttribute("mensagemErro", "Atenção, login já existente, escolha outro");
			return "/publica-criar-usuario";	
		}
		
		//Busca o papel básico do usuário
		Papel papel = papelRepository.findByPapel("USER");		
		List<Papel>papeis = new ArrayList<Papel>();
		
		papeis.add(papel);
		
		usuario.setPapeis(papeis);
		
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
	
	
	@RequestMapping(value = "editarPapel/{id}")
	public String alterarPapeis(@PathVariable Long id, Model model) {
		
		Usuario usuario = usuarioRepository.findById(id).get();
		List<Papel>papeis = papelRepository.findAll();
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("listaPapeis", papeis	);
		
		return "auth/admin/user-alterar-papeis";
	}
	
	
	@PostMapping(value = "editarPapel/{id}")
	public String atribuirPaeis(@PathVariable("id") Long idUsuario
			                  , @RequestParam(value = "pps", required = false)int[]pps
			                  , Usuario usuario
							  , RedirectAttributes attr) {		
		
		if(pps == null) {
			
			attr.addFlashAttribute("mensagem", "Pelo menos um papel deve ser informado");
			return "redirect:/usuario/editarPapel/"+idUsuario;
		}
		
		List<Papel>papeis = new ArrayList<>();
		
		for (int i : pps) {
			long idUser = i;
			Optional<Papel> papel = papelRepository.findById(idUser);
			System.out.println(papel.get().getPapel());
			papeis.add(papel.get());
		}

		Optional<Usuario>usr = usuarioRepository.findById(idUsuario);
		
		if(usr.isPresent()) {
			Usuario usuarioGet = usr.get();
			usuarioGet.setPapeis(papeis);
			usuarioRepository.save(usuarioGet);
			attr.addFlashAttribute("mensagemAlterar", "Ok");
			return "redirect:/usuario/admin/listar/";
		}	
		

		attr.addFlashAttribute("mensagem", "Erro ao salvar");
		return "redirect:/usuario/editarPapel/"+idUsuario;
		
		
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
	
	@GetMapping(value = "/testeLoginExiste")
	@ResponseBody
	public String testaLogin(@RequestParam String login) {
		Usuario usuario = usuarioRepository.findByLogin(login);
		
		String retorno = usuario.getNome();
		
		retorno += ' '+ usuario.getPapeis().get(0).getPapel();
		
		return retorno;
		
	}
	
	@GetMapping(value = "/buscaTodosUsuarios")
	@ResponseBody
	public String listarTodosUsu() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		for (Usuario usuario : usuarios) {
			System.out.print(usuario.getNome()+"\n");
			
			List<Papel>papeis = usuario.getPapeis();
			
			for (Papel papel : papeis) {
				System.out.println("# Papel => "+papel.getPapel());
			}
		}
		
		return "OK";
		
	}
	
	

}
