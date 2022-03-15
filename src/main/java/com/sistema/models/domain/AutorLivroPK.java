package com.sistema.models.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AutorLivroPK implements Serializable {

	private static final long serialVersionUID = 527897366719783447L;

	private Long idAutor;
	private Long idLivro;
	
	
	public AutorLivroPK() {
	}


	public AutorLivroPK(Long idAutor, Long idLivro) {
		this.idAutor = idAutor;
		this.idLivro = idLivro;
	}


	@Column(name="ID_AUTOR", insertable = false, updatable = false, nullable = false)
	public Long getIdAutor() {
		return idAutor;
	}


	public void setIdAutor(Long idAutor) {
		this.idAutor = idAutor;
	}

	@Column(name="ID_LIVRO", insertable = false, updatable = false, nullable = false)
	public Long getIdLivro() {
		return idLivro;
	}


	public void setIdLivro(Long idLivro) {
		this.idLivro = idLivro;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAutor == null) ? 0 : idAutor.hashCode());
		result = prime * result + ((idLivro == null) ? 0 : idLivro.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AutorLivroPK other = (AutorLivroPK) obj;
		if (idAutor == null) {
			if (other.idAutor != null)
				return false;
		} else if (!idAutor.equals(other.idAutor))
			return false;
		if (idLivro == null) {
			if (other.idLivro != null)
				return false;
		} else if (!idLivro.equals(other.idLivro))
			return false;
		return true;
	}
	
	
	
}
