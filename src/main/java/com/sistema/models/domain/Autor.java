package com.sistema.models.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TAB_AUTOR")
public class Autor {
	
	private Long   id;
	private String nome;
	private Date   dataNascimento;
	private String rg;
	private String cpf; 
	private String sexo;
	private String endereco;
	private String bairro; 
	private String cidade;
	private String cep;
	private String email;
	private String telefoneFixo;
	private String telefoneMovel;
	
	private Editora editora;
	
	private List<Livro> livros;

	public Autor() {
	}


	public Autor(Long id, String nome, Date dataNascimento, String rg, String cpf, String sexo, String endereco,
			String bairro, String cidade, String cep, String email, String telefoneFixo, String telefoneMovel,
			Editora editora) {
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.rg = rg;
		this.cpf = cpf;
		this.sexo = sexo;                    
		this.endereco = endereco;
		this.bairro = bairro;
		this.cidade = cidade;
		this.cep = cep;
		this.email = email;
		this.telefoneFixo = telefoneFixo;
		this.telefoneMovel = telefoneMovel;
		this.editora = editora;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_AUTOR")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NOME_AUTOR", length = 80, nullable = false )
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "DATA_NASCIMENTO_AUTOR", nullable = false )
	public Date getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Column(name = "RG_AUTOR", length = 20, nullable = false )
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Column(name = "CPF_AUTOR", length = 20, nullable = false )
	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

    @Column(name = "SEXO_AUTOR", length = 1, nullable = false )
	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Column(name = "ENDERECO_AUTOR", length = 80, nullable = false )
	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Column(name = "BAIRRO_AUTOR", length = 50, nullable = false )
	public String getBairro() {
		return bairro;
	}


	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Column(name = "CIDADE_AUTOR", length = 50, nullable = false )
	public String getCidade() {
		return cidade;
	}


	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	
	@Column(name = "CEP_AUTOR", length = 10, nullable = true )
	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}

	@Column(name = "EMAIL_AUTOR", length = 50, nullable = false )
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Column(name = "TEL_FIXO_AUTOR", length = 15, nullable = false )
	public String getTelefoneFixo() {
		return telefoneFixo;
	}


	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	@Column(name = "TEL_MOVEL_AUTOR", length = 20, nullable = false )
	public String getTelefoneMovel() {
		return telefoneMovel;
	}


	public void setTelefoneMovel(String telefoneMovel) {
		this.telefoneMovel = telefoneMovel;
	}
	
	@JsonIgnore
    @ManyToOne
    @JoinColumn(name="ID_EDITORA")
	public Editora getEditora() {
		return editora;
	}


	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	
	@Size(min=1, message = "obrigatório informar um livro ")
    @ManyToMany
    @JoinTable(name = "TAB_AUTOR_LIVRO", 
    		   joinColumns = @JoinColumn(name="ID_AUTOR"),
    		   inverseJoinColumns = @JoinColumn(name="ID_LIVRO"))
	public List<Livro> getLivros() {
		return livros;
	}


	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

//    @OneToMany(mappedBy = "autor")	
// 	public List<AutorLivro> getListaAutoresLivros() {
//		return listaAutoresLivros;
//	}
//
//
//	public void setListaAutoresLivros(List<AutorLivro> listaAutoresLivros) {
//		this.listaAutoresLivros = listaAutoresLivros;
//	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Autor other = (Autor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", rg=" + rg + ", cpf="
				+ cpf + ", sexo=" + sexo + ", endereco=" + endereco + ", bairro=" + bairro + ", cidade=" + cidade
				+ ", cep=" + cep + ", email=" + email + ", telefoneFixo=" + telefoneFixo + ", telefoneMovel="
				+ telefoneMovel + ", editora=" + editora + "]";
	}


	
	

}
