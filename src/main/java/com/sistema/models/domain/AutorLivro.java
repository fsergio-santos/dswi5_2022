package com.sistema.models.domain;

/*
 * import java.util.Date;
 * 
 * import javax.persistence.EmbeddedId; import javax.persistence.Entity; import
 * javax.persistence.JoinColumn; import javax.persistence.ManyToOne; import
 * javax.persistence.MapsId; import javax.persistence.Table;
 * 
 * @Entity
 * 
 * @Table(name="TAB_AUTOR_LIVRO") public class AutorLivro {
 * 
 * 
 * private AutorLivroPK id; private Date dataCadastro;
 * 
 * private Autor autor; private Livro livro;
 * 
 * 
 * public AutorLivro() { }
 * 
 * public AutorLivro(AutorLivroPK id, Date dataCadastro) { this.id = id;
 * this.dataCadastro = dataCadastro; }
 * 
 * @EmbeddedId public AutorLivroPK getId() { return id; }
 * 
 * public void setId(AutorLivroPK id) { this.id = id; }
 * 
 * public Date getDataCadastro() { return dataCadastro; }
 * 
 * public void setDataCadastro(Date dataCadastro) { this.dataCadastro =
 * dataCadastro; }
 * 
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name="ID_AUTOR", insertable = false, updatable = false, nullable
 * = false)
 * 
 * @MapsId(value = "idAutor") public Autor getAutor() { return autor; }
 * 
 * public void setAutor(Autor autor) { this.autor = autor; }
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name="ID_LIVRO", insertable = false, updatable = false, nullable
 * = false)
 * 
 * @MapsId(value = "idLivro") public Livro getLivro() { return livro; }
 * 
 * public void setLivro(Livro livro) { this.livro = livro; }
 * 
 * @Override public int hashCode() { final int prime = 31; int result = 1;
 * result = prime * result + ((id == null) ? 0 : id.hashCode()); return result;
 * }
 * 
 * @Override public boolean equals(Object obj) { if (this == obj) return true;
 * if (obj == null) return false; if (getClass() != obj.getClass()) return
 * false; AutorLivro other = (AutorLivro) obj; if (id == null) { if (other.id !=
 * null) return false; } else if (!id.equals(other.id)) return false; return
 * true; }
 * 
 * @Override public String toString() { return "AutorLivro [id=" + id +
 * ", dataCadastro=" + dataCadastro + "]"; }
 * 
 * 
 * 
 * 
 * 
 * }
 */