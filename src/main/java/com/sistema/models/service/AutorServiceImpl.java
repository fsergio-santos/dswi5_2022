package com.sistema.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.models.domain.Autor;
import com.sistema.models.repository.AutorRepository;
import com.sistema.models.service.faces.AutorService;

@Service
@Transactional
public class AutorServiceImpl implements AutorService {
    
	@Autowired
	private AutorRepository autorRepository;
		
	@Override
	public Autor save(Autor autor) {      
		return autorRepository.saveAndFlush(autor);
	}

	@Override
	public Autor update(Autor autor) {
		return save(autor);
	}

	@Override
	public void deleteById(Long id) {    
		autorRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Autor> findById(Long id) {
		return autorRepository.findById(id);
	}

	@Override
	public List<Autor> findAll() {
		return autorRepository.findAll();
	}

	@Override
	public Page<Autor> findAll(Pageable pageable) {
		return autorRepository.findAll(pageable);
	}

	@Override
	public Page<Autor> findAutorByName(String nome, Pageable pageable) {
		return autorRepository.findAutorByName(nome, pageable);
	}

}







//Autor autorUpdate = null;
//Optional<Autor> autorCadastrado = findById(id);
//if (autorCadastrado.isPresent()) {
//	autorUpdate = autorCadastrado.get();
//	autorUpdate.setBairro(autor.getBairro());
//	autorUpdate.setCep(autor.getCep());
//	autorUpdate.setCidade(autor.getCidade());
//	autorUpdate.setCpf(autor.getCpf());
//	autorUpdate.setDataNacismento(autor.getDataNacismento());
//	autorUpdate.setEditora(autor.getEditora());
//	autorUpdate.setEmail(autor.getEmail());
//	autorUpdate.setEndereco(autor.getEndereco());
//	autorUpdate.setNome(autor.getNome());
//	autorUpdate.setRg(autor.getRg());
//	autorUpdate.setSexo(autor.getSexo());
//	autorUpdate.setTelefoneFixo(autor.getTelefoneFixo());
//	autorUpdate.setTelefoneMovel(autor.getTelefoneMovel());
//	autorUpdate.setLivros(autor.getLivros());
//}