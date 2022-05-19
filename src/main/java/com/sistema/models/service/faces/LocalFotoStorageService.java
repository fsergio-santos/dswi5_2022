package com.sistema.models.service.faces;

import java.io.InputStream;
import java.nio.file.Path;

import com.sistema.models.domain.Usuario;
import com.sistema.models.domain.dto.Foto;

public interface LocalFotoStorageService {
	
	public Foto armazenar(Foto foto);
	public Usuario buscarUsuario(Long id);
	public Foto excluirFoto(Foto foto);
	public boolean remover(String foto);
	public InputStream recuperar(String foto);
	public byte[] recuperarFoto(String nomeArquivo);
	public String gerarNomeArquivoFoto(String nomeArquivo);
	public Path getArquivoPath(String nomeArquivo);
	
	

}
