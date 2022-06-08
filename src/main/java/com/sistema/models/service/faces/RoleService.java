package com.sistema.models.service.faces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sistema.models.domain.Role ;

public interface RoleService {
	List<Role> findAll();
	Role save(Role  role );
	Role update(Role  role );
	void deleteById(Long id);
	Role findById(Long id);
	Page<Role> findAll(Pageable pageable);
	Page<Role> findRoleByName(String nome, Pageable pageable);
}
