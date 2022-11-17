package com.br.springthymeleaf.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.br.springthymeleaf.model.Historico;
import com.br.springthymeleaf.model.HistoricoUsuario;
import com.br.springthymeleaf.model.Papel;
import com.br.springthymeleaf.model.Usuario;
import com.br.springthymeleaf.repository.HistoricoRepository;
import com.br.springthymeleaf.repository.HistoricoUsuarioRepository;
import com.br.springthymeleaf.repository.PapelRepository;
import com.br.springthymeleaf.repository.UsuarioRepository;

@Component
public class CarregadorDados implements CommandLineRunner {
	
	@Autowired
	private UsuarioRepository usuRepo;
	
	@Autowired
	private PapelRepository papelRepository;
	
	@Autowired
	private HistoricoRepository historicoRepository;
	
	@Autowired
	private HistoricoUsuarioRepository historicoUsuarioRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		String[]papeis = {"ADMIN" , "USER" , "BIBLIOTECARIO"};
		
		for (String papelString : papeis) {
			
			Papel papel = papelRepository.findByPapel(papelString);
			
			if(papel == null) {
				papel = new Papel(papelString);
				papelRepository.save(papel);
			}
			
		}		
		
		
		//Criando históricos
		String[]historicos = {"CRIACAO_USU" , "ENVIO_EMAIL" , "ALTERACAO_IDADE"};
				
		for (String historico : historicos) {
			
			Historico hist = historicoRepository.findByNomeHistorico(historico);
			
			if(hist == null) {			
				System.out.println(historico);
				hist = new Historico(historico);
				historicoRepository.save(hist);
			}
		}		
		
		/*
		
		//Criando históricos usuario 
		Historico hist = new Historico();
		HistoricoUsuario histUsu;
		Usuario usu = new Usuario();
		
		hist.setId((long) 1);
		usu.setId( (long) 36);
		histUsu = new HistoricoUsuario("CRIAÇÃO USUARIO OMARP",hist,usu);
		historicoUsuarioRepository.save(histUsu);
		

		hist.setId((long) 2);
		histUsu = new HistoricoUsuario("ENVIANDO EMAIL PARA OMARP",hist,usu);
		historicoUsuarioRepository.save(histUsu);	

		hist.setId((long) 3);
		usu.setId( (long) 37);
		histUsu = new HistoricoUsuario("ALTERANDO IDADE OMARP PARA 37",hist,usu);
		historicoUsuarioRepository.save(histUsu);	
		
		
		List<Historico>historicoArray = new ArrayList<>();
		
		historicoArray = historicoRepository.findAll();		

		System.out.println("################  HISTÓRICOS   #################");
		
		for (Historico historico : historicoArray) {
			System.out.println(historico.getNomeHistorico());
			
			List<HistoricoUsuario>histUsuarioArray = historico.getHistoricosUsuario();
						
			for (HistoricoUsuario historico2 : histUsuarioArray) {

				System.out.println("## "+historico2.getDescricaoHistorico());
			}
			
		}
		
		
		
		
		System.out.println("");
		
		List<Usuario>usuariosArray = usuRepo.findAll();
		System.out.println("################  USUÁRIOS   #################");	
		for(Usuario listaUsu: usuariosArray) {
			System.out.println(listaUsu.getNome());
			
			List<HistoricoUsuario>historicosUsuarioArray = listaUsu.getHistoricosUsuario();
			//System.out.println("#### "+historicosUsuarioArray);
			
			for(HistoricoUsuario histUsuItem: historicosUsuarioArray){
				System.out.println("#### "+histUsuItem.getDescricaoHistorico());
			}
		}
	
		*/
	}
	
	

}
