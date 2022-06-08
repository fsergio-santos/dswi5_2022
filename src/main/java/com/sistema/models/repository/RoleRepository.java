package com.sistema.models.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistema.models.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	@Query("SELECT r FROM Role r WHERE r.nome LIKE(CONCAT('%', :nome ,'%'))")
	Page<Role> findRoleByName(@Param("nome") String nome, Pageable pageable);
	

}
