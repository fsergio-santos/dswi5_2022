package com.sistema.models.repository;

bi111296

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.models.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query("SELECT u FROM Usuario u WHERE u.username LIKE(CONCAT('%', :nome ,'%'))")
	Page<Usuario> findUsuarioByName(@Param("nome") String nome, Pageable pageable);
	
	
	

}
