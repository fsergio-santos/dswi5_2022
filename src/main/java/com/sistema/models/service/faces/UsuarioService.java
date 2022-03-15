package com.sistema.models.service.faces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sistema.models.domain.Usuario ;

public interface UsuarioService {

	List<Usuario> findAll();
	Usuario save(Usuario  usuario );
	Usuario update(Usuario  usuario );
	void deleteById(Long id);
	Usuario findById(Long id);
	Page<Usuario> findAll(Pageable pageable);
	Page<Usuario> findUsuarioByName(String nome, Pageable pageable);
}
