package br.com.bootcamp.nossoBancoDigital.conta.controller.dto;

import org.springframework.web.multipart.MultipartFile;

import br.com.bootcamp.nossoBancoDigital.proposta.upload.Uploader;



public class DocumentoUploadForm {
	
	
	private MultipartFile documento;
	
	

	public String uploadFoto(Uploader uploader) {
		return uploader.upload(documento);
	}
	
	
	public MultipartFile getDocumento() {
		return documento;
	}


	public void setDocumento(MultipartFile documento) {
		this.documento = documento;
	}
	
	


	

}
