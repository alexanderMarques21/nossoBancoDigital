package br.com.bootcamp.nossoBancoDigital.proposta.upload;

import org.springframework.web.multipart.MultipartFile;

public interface Uploader  {
	
	public String upload(MultipartFile foto);

}
