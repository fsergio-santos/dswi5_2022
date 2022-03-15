package com.sistema.models.service.faces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sistema.models.domain.Livro;

public interface LivroService {

	Livro save(Livro livro);
	Livro update(Livro livro);
	void deleteById(Long id);
	Optional<Livro> findById(Long id);
	List<Livro> findAll();
	Page<Livro> findAll(Pageable pageable);
	Page<Livro> findLivroByName(String nome, Pageable pageable);
}
