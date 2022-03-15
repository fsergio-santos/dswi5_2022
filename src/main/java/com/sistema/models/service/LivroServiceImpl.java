package com.sistema.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.models.domain.Livro;
import com.sistema.models.repository.LivroRepository;
import com.sistema.models.service.faces.LivroService;

@Service
@Transactional
public class LivroServiceImpl implements LivroService {

	@Autowired
	private LivroRepository livroRepository;
	
	@Override
	public Livro save(Livro livro) {
		return livroRepository.save(livro);
	}
	
	@Override
	public Livro update(Livro livro) {
		return save(livro);
	}

	@Override
	public void deleteById(Long id) {
		livroRepository.deleteById(id);	
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Livro> findById(Long id) {
		return livroRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Livro> findAll() {
		return livroRepository.findAll();
	}

	@Override
	public Page<Livro> findAll(Pageable pageable) {
		return livroRepository.findAll(pageable);
	}

	@Override
	public Page<Livro> findLivroByName(String nome, Pageable pageable) {
		return livroRepository.findLivroByName(nome, pageable);
	}

}
