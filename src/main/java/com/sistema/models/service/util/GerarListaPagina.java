package com.sistema.models.service.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class GerarListaPagina {

	
	public static Pageable gerarPagina(Integer paginaAtual,Integer tamanhoPagina, String dir, String atributo ) {
		return PageRequest.of(paginaAtual, tamanhoPagina, getDirection(dir), atributo);
	}
	
	private static Direction getDirection(String dir) {
		return dir.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
	}

}
