package br.com.bootcamp.nossoBancoDigital.proposta.controller.dto;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import br.com.bootcamp.nossoBancoDigital.proposta.upload.Uploader;

public class FotoDocumentoForm {


	@NotNull
	private MultipartFile foto;

	public FotoDocumentoForm(@NotNull MultipartFile foto) {
		this.foto = foto;
	}

	public String novaFotoDocumento(Uploader uploader) {

		return uploader.upload(foto);

	}


	public MultipartFile getFoto() {
		return foto;
	}

}
