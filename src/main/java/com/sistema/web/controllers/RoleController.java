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

import com.sistema.models.domain.Role;
import com.sistema.models.service.faces.RoleService;
import com.sistema.models.service.util.GerarListaPagina;

@RestController
@RequestMapping(value="/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@ResponseBody
	@GetMapping(value="/listar")
	public Page<Role> findAll(
			@RequestParam(value="paginaAtual",required=false) Optional<Integer> paginaAtual,
			@RequestParam(value="tamanhoPagina",required=false) Optional<Integer> tamanhoPagina,
			@RequestParam(value="atributo",required=false) Optional<String> atributo,
			@RequestParam(value="dir",required=false) Optional<String> dir ){
		
		    Pageable pageable = GerarListaPagina.gerarPagina(paginaAtual.orElse(0), 
		    		                        tamanhoPagina.orElse(5), 
		    		                        dir.orElse("asc"), 
		    		                        atributo.orElse("id") );
		    
		    Page<Role> paginaRole = roleService.findAll(pageable);
		
		return paginaRole; 
	}
	
	
	@ResponseBody
	@GetMapping(value="/listar/{nome}")
	public Page<Role> findRoleByName(
			@PathVariable("nome") String nome,
			@RequestParam(value="paginaAtual",required=false) Optional<Integer> paginaAtual,
			@RequestParam(value="tamanhoPagina",required=false) Optional<Integer> tamanhoPagina,
			@RequestParam(value="atributo",required=false) Optional<String> atributo,
			@RequestParam(value="dir",required=false) Optional<String> dir ){
		
		    Pageable pageable = GerarListaPagina.gerarPagina(paginaAtual.orElse(0), 
		    		                        tamanhoPagina.orElse(5), 
		    		                        dir.orElse("asc"), 
		    		                        atributo.orElse("id") );
		    
		    Page<Role> paginaRole = roleService.findRoleByName(nome, pageable);
		
		return paginaRole; 
	}
	
	@ResponseBody
	@PostMapping(value="/inserir")
	public Role inserir(@RequestBody Role role) {
	 	return roleService.save(role);
	}
	
	
	@ResponseBody
	@PostMapping(value="/alterar")
	public Role update(@RequestBody Role role) {
	 	return roleService.update(role);
	}
	

	@ResponseBody
	@DeleteMapping(value="/delete/{id}")
	public void deleteById(@PathVariable("id") Long id) {
		roleService.deleteById(id);
	}
	
	
	@ResponseBody
	@GetMapping(value="/buscar/{id}")
	public Role findById(@PathVariable("id") Long id) {
		return roleService.findById(id);
		
	}
	
}
