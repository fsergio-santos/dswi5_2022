package com.sistema.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.models.domain.Editora;
import com.sistema.models.repository.EditoraRepository;
import com.sistema.models.service.faces.EditoraService;

@Service
@Transactional
public class EditoraServiceImpl implements EditoraService {
	
	@Autowired
	private EditoraRepository editoraRepository;
	
	@Override
	public Editora save(Editora editora) {
		return editoraRepository.save(editora);
	}

	@Override
	public void update(Editora editora) {
		save(editora);
	}

	@Override
	public void deleteById(Long id) {
		editoraRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Editora> findById(Long id) {
		return editoraRepository.findById(id);
	}

	@Override
	public List<Editora> findAll() {
		return editoraRepository.findAll();
	}

}
