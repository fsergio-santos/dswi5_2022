package com.sistema.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.models.domain.Editora;
import com.sistema.models.service.faces.EditoraService;
import com.sistema.web.response.ResponseMessage;

@RestController
@RequestMapping(value="/editora")
public class EditoraController {

	@Autowired
	private EditoraService editoraService;
	
	@ResponseBody
	@GetMapping(value="/listar")
	public List<Editora> findAll(){
		return editoraService.findAll();
	}
		
	@ResponseBody
	@PostMapping(value="/inserir")
	public ResponseEntity<Editora> save(@RequestBody Editora editora){
		Editora editoraGravada = editoraService.save(editora);
		return ResponseEntity.ok(editoraGravada);
	}
		
	@ResponseBody
	@DeleteMapping(value="/delete/{id}")
	public void deleteById(@PathVariable("id") Long id) {
		editoraService.deleteById(id);
	}
	
	@ResponseBody
	@GetMapping(value="/buscar/{id}")
	public Optional<Editora> findById(@PathVariable("id") Long id) {
		return editoraService.findById(id);
	}
	
	
}
