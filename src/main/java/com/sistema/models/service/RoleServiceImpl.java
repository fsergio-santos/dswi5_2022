package com.sistema.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.models.domain.Role;
import com.sistema.models.repository.RoleRepository;
import com.sistema.models.service.exception.EntidadeNaoCadastrada;
import com.sistema.models.service.exception.IdNaoPodeSerZeroOuNulo;
import com.sistema.models.service.faces.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role update(Role role) {
		return save(role);
	}

	@Override
	public void deleteById(Long id) {
		roleRepository.deleteById(id);
	}

	@Override
	public Role findById(Long id) {
	    if (id <= 0) {
	    	throw new IdNaoPodeSerZeroOuNulo("Identificador da role inválido");
	    }
		return roleRepository.findById(id).orElseThrow(()-> new EntidadeNaoCadastrada("Usuário não Encontrado!")) ;
	}

	@Override
	public Page<Role> findAll(Pageable pageable) {
		return roleRepository.findAll(pageable);
	}

	@Override
	public Page<Role> findRoleByName(String nome, Pageable pageable) {
		return roleRepository.findRoleByName(nome, pageable);
	}

	

}
