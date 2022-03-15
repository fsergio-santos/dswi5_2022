package com.sistema.models.service.faces;

import java.util.List;
import java.util.Optional;
import com.sistema.models.domain.Editora;

public interface EditoraService {

	Editora save(Editora editora);
	void update(Editora editora);
	void deleteById(Long id);
	Optional<Editora> findById(Long id);
	List<Editora> findAll();
}
