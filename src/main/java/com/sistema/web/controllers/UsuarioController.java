package com.sistema.web.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.models.domain.Usuario;
import com.sistema.models.service.faces.UsuarioService;
import com.sistema.models.service.util.GerarListaPagina;

@RestController
@RequestMapping(value="/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@ResponseBody
	@GetMapping(value="/listar")
	public Page<Usuario> findAll(
			@RequestParam(value="paginaAtual",required=false) Optional<Integer> paginaAtual,
			@RequestParam(value="tamanhoPagina",required=false) Optional<Integer> tamanhoPagina,
			@RequestParam(value="atributo",required=false) Optional<String> atributo,
			@RequestParam(value="dir",required=false) Optional<String> dir ){
		
		    Pageable pageable = GerarListaPagina.gerarPagina(paginaAtual.orElse(0), 
		    		                        tamanhoPagina.orElse(5), 
		    		                        dir.orElse("asc"), 
		    		                        atributo.orElse("id") );
		    
		    Page<Usuario> paginaUsuario = usuarioService.findAll(pageable);
		
		return paginaUsuario; 
	}
	
	
	@ResponseBody
	@GetMapping(value="/listar/{nome}")
	public Page<Usuario> findUsuarioByName(
			@PathVariable("nome") String nome,
			@RequestParam(value="paginaAtual",required=false) Optional<Integer> paginaAtual,
			@RequestParam(value="tamanhoPagina",required=false) Optional<Integer> tamanhoPagina,
			@RequestParam(value="atributo",required=false) Optional<String> atributo,
			@RequestParam(value="dir",required=false) Optional<String> dir ){
		
		    Pageable pageable = GerarListaPagina.gerarPagina(paginaAtual.orElse(0), 
		    		                        tamanhoPagina.orElse(5), 
		    		                        dir.orElse("asc"), 
		    		                        atributo.orElse("id") );
		    
		    Page<Usuario> paginaUsuario = usuarioService.findUsuarioByName(nome, pageable);
		
		return paginaUsuario; 
	}
	
	@ResponseBody
	@PostMapping(value="/inserir")
	public Usuario inserir(@RequestBody Usuario usuario) {
		System.out.println(usuario.toString());
	 	return null;
	 			//usuarioService.save(usuario);
	}
	
	
	@ResponseBody
	@PostMapping(value="/alterar")
	public Usuario update(@RequestBody Usuario usuario) {
	 	return usuarioService.update(usuario);
	}
	

	@ResponseBody
	@DeleteMapping(value="/delete/{id}")
	public void deleteById(@PathVariable("id") Long id) {
		usuarioService.deleteById(id);
	}
	
	
	@ResponseBody
	@GetMapping(value="/buscar/{id}")
	public Optional<Usuario> findById(@PathVariable("id") Long id) {
		return null;
				//usuarioService.findById(id);
		
	}
	
}
