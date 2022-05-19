package com.sistema.web.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.models.domain.dto.Foto;
import com.sistema.models.domain.dto.FotoRequest;
import com.sistema.models.service.exception.FileStorageException;
import com.sistema.models.service.faces.LocalFotoStorageService;

@RestController
@RequestMapping(value="/foto")
public class FotoController {
	
	@Autowired
	private LocalFotoStorageService localFotoStorageService;
	
	@PostMapping(value="/gravar", consumes=MediaType.MULTIPART_FORM_DATA_VALUE,
			                      produces=MediaType.APPLICATION_JSON_VALUE  )
	public ResponseEntity<Foto> uploadFoto(FotoRequest fotoRequest){
		
		Long id = 0L;
		if  (!"".equals(fotoRequest.getId())) {
			id = Long.valueOf(fotoRequest.getId());
		}
		
		Foto foto = new Foto();
		try {
			foto.setId(id);
			foto.setNomeArquivo(fotoRequest.getFoto().getOriginalFilename());
			foto.setInputStream(fotoRequest.getFoto().getInputStream());
			foto.setContentType(fotoRequest.getFoto().getContentType());
			foto = localFotoStorageService.armazenar(foto);
		} catch (IOException e) {
			throw new FileStorageException("Falha na gravação do arquivo da foto");
		}
		return ResponseEntity.ok().body(foto);
	}
	
	
	@DeleteMapping(value="/delete/{nomeFoto:.+}")
	public ResponseEntity<Foto> excluirFoto(@PathVariable String nomeFoto){
		
		Foto foto = new Foto(); 
		
		foto.setId(0L);
        foto.setNomeArquivo(nomeFoto);
        
        foto = localFotoStorageService.excluirFoto(foto);
		
		return null;
	}
	
	
	@GetMapping("/{nomeFoto:.+}")
	public byte[] recuperarFoto(@PathVariable String nomeFoto) {
		return localFotoStorageService.recuperarFoto(nomeFoto);
	}
	
	

}
