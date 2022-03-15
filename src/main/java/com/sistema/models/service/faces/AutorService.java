package com.sistema.models.service.faces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sistema.models.domain.Autor;


public interface AutorService {
	
	List<Autor> findAll();
	Autor save(Autor autor);
	Autor update(Autor autor);
	void deleteById(Long id);
	Optional<Autor> findById(Long id);
	Page<Autor> findAll(Pageable pageable);
	Page<Autor> findAutorByName(String nome, Pageable pageable);

}
