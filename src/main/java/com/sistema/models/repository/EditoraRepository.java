package com.sistema.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.models.domain.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long>{

}
