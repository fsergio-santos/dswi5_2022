package com.sistema.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.models.domain.Usuario;
import com.sistema.models.repository.UsuarioRepository;
import com.sistema.models.service.exception.EmailJaCadastradoExecption;
import com.sistema.models.service.exception.EntidadeNaoCadastrada;
import com.sistema.models.service.exception.IdNaoPodeSerZeroOuNulo;
import com.sistema.models.service.exception.SenhaDiferenteException;
import com.sistema.models.service.faces.UsuarioService;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	private PasswordEncoder passwordEnconder;

	@Override
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario save(Usuario usuario) {
		
		Optional<Usuario> usuarioCadastrado = findUsuarioByEmail(usuario.getEmail());
		
		if ( usuarioCadastrado.isPresent() && usuarioCadastrado.get().equals(usuario)) {
			throw new EmailJaCadastradoExecption("E-mail já cadastrado!");
		}
		
		if (!usuario.getPassword().equals(usuario.getConfirmPassword())) {
			throw new SenhaDiferenteException("As senhas estão diferentes!");
		}
		
		
		
		
		usuario.setPassword(encodePassword(usuario.getPassword()));
		
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario update(Usuario usuario) {
		return save(usuario);
	}

	@Override
	public void deleteById(Long id) {
		usuarioRepository.deleteById(id);
	}

	@Override
	public Usuario findById(Long id) {
	    if (id <= 0) {
	    	throw new IdNaoPodeSerZeroOuNulo("Identificador do usuário inválido");
	    }
		return usuarioRepository.findById(id).orElseThrow(()-> new EntidadeNaoCadastrada("Usuário não Encontrado!")) ;
	}

	@Override
	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}

	@Override
	public Page<Usuario> findUsuarioByName(String nome, Pageable pageable) {
		return usuarioRepository.findUsuarioByName(nome, pageable);
	}

	@Override
	public Optional<Usuario> findUsuarioByEmail(String email) {
		return usuarioRepository.findUsuarioByEmail(email);
	}
	
	private String encodePassword(String password) {
		return passwordEnconder.encode(password);
	}

}
