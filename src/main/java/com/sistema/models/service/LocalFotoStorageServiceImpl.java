package com.sistema.models.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.sistema.models.domain.Usuario;
import com.sistema.models.domain.dto.Foto;
import com.sistema.models.service.exception.FileStorageException;
import com.sistema.models.service.faces.LocalFotoStorageService;
import com.sistema.models.service.faces.UsuarioService;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

@Service
public class LocalFotoStorageServiceImpl implements LocalFotoStorageService{

	public static final String DIRETORIO_DAS_FOTOS = "/nds/catalogo";
	
	private Path diretorioFotos;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	public LocalFotoStorageServiceImpl() {
		try {
	    	diretorioFotos = Paths.get(DIRETORIO_DAS_FOTOS)
	    			              .toAbsolutePath().normalize();
			Files.createDirectories(diretorioFotos);
		} catch (IOException e) {
			throw new FileStorageException("Não foi possível "
					+ "criar diretório das fotos", e);
		}
	}
	
	
	@Override
	public Foto armazenar(Foto foto) {
		String nomeFoto = null;
		Usuario usuario = buscarUsuario(foto.getId());
		if (!Objects.isNull(usuario)) {
			remover(usuario.getFoto());
		}
		nomeFoto = gerarNomeArquivoFoto(foto.getNomeArquivo());
		try {
			Path arquivoPath = getArquivoPath(nomeFoto);
			FileCopyUtils.copy(foto.getInputStream(), Files.newOutputStream(arquivoPath));
			Thumbnails.of(arquivoPath.toString())
			          .size(50,78)
			          .toFiles(Rename.PREFIX_DOT_THUMBNAIL);
			foto.setNomeArquivo(nomeFoto);
			return foto;
		} catch (IOException e) {
			throw new FileStorageException("Erro ao gravar a foto", e);
		}
	}

	@Override
	public Usuario buscarUsuario(Long id) {
		Usuario usuario = null;
		if (id != 0L) {
		    usuario = usuarioService.findById(id);
		}
		return usuario;
	}

	@Override
	public Foto excluirFoto(Foto foto) {
		Usuario usuario = buscarUsuario(foto.getId());
		String nomeFoto = Objects.isNull(usuario) 
				        ? foto.getNomeArquivo() 
				        : usuario.getFoto(); 
		if (remover(nomeFoto)) {
		    if (!Objects.isNull(usuario)) {
		    	usuarioService.updateFoto(usuario.getId(),"");
		    }
		}
		foto.setNomeArquivo("");
		return foto;
	}

	@Override
	public boolean remover(String foto) {
        String thumbnail = "thumbnail."+foto;
        if (!foto.isEmpty()) {
	        try {
	        	Path arquivoThumbnailPath = getArquivoPath(thumbnail);
				Files.deleteIfExists(arquivoThumbnailPath);
				
				Path arquivoPath = getArquivoPath(foto);
				Files.deleteIfExists(arquivoPath);
				return true;
			} catch (IOException e) {
		       throw new FileStorageException("Erro na exclusão da foto"); 
			}
   		
        }
        return false; 
	}
	
	@Override
	public InputStream recuperar(String foto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] recuperarFoto(String nomeArquivo) {
		try {
			return Files.readAllBytes(getArquivoPath(nomeArquivo));
		} catch (IOException e) {
			throw new FileStorageException("Erro na recuperação da foto", e);
		}
	}

	@Override
	public String gerarNomeArquivoFoto(String nomeArquivo) {
		return UUID.randomUUID().toString()+"_"+nomeArquivo;
	}
	
	@Override
	public Path getArquivoPath(String nomeArquivo) {
		return diretorioFotos.resolve(Paths.get(nomeArquivo));
	}
}
