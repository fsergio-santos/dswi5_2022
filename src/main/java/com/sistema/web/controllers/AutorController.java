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

import com.sistema.models.domain.Autor;
import com.sistema.models.service.faces.AutorService;
import com.sistema.models.service.util.GerarListaPagina;

@RestController
@RequestMapping(value="/autor")
public class AutorController {
	
	@Autowired
	private AutorService autorService;
	
	@ResponseBody
	@GetMapping(value="/listar")
	public Page<Autor> findAll(
			@RequestParam(value="paginaAtual",required=false) Optional<Integer> paginaAtual,
			@RequestParam(value="tamanhoPagina",required=false) Optional<Integer> tamanhoPagina,
			@RequestParam(value="atributo",required=false) Optional<String> atributo,
			@RequestParam(value="dir",required=false) Optional<String> dir ){
		
		    Pageable pageable = GerarListaPagina.gerarPagina(paginaAtual.orElse(0), 
		    		                        tamanhoPagina.orElse(5), 
		    		                        dir.orElse("asc"), 
		    		                        atributo.orElse("id") );
		    
		    Page<Autor> paginaAutor = autorService.findAll(pageable);
		
		return paginaAutor; 
	}
	
	
	@ResponseBody
	@GetMapping(value="/listar/{nome}")
	public Page<Autor> findAutorByName(
			@PathVariable("nome") String nome,
			@RequestParam(value="paginaAtual",required=false) Optional<Integer> paginaAtual,
			@RequestParam(value="tamanhoPagina",required=false) Optional<Integer> tamanhoPagina,
			@RequestParam(value="atributo",required=false) Optional<String> atributo,
			@RequestParam(value="dir",required=false) Optional<String> dir ){
		
		    Pageable pageable = GerarListaPagina.gerarPagina(paginaAtual.orElse(0), 
		    		                        tamanhoPagina.orElse(5), 
		    		                        dir.orElse("asc"), 
		    		                        atributo.orElse("id") );
		    
		    Page<Autor> paginaAutor = autorService.findAutorByName(nome, pageable);
		
		return paginaAutor; 
	}
	
	@ResponseBody
	@PostMapping(value="/inserir")
	public Autor inserir(@RequestBody Autor autor) {
	 	return autorService.save(autor);
	}
	
	
	@ResponseBody
	@PostMapping(value="/alterar")
	public Autor update(@RequestBody Autor autor) {
	 	return autorService.update(autor);
	}
	

	@ResponseBody
	@DeleteMapping(value="/delete/{id}")
	public void deleteById(@PathVariable("id") Long id) {
		autorService.deleteById(id);
	}
	
	
	@ResponseBody
	@GetMapping(value="/buscar/{id}")
	public Optional<Autor> findById(@PathVariable("id") Long id) {
		return autorService.findById(id);
		
	}
	
}
