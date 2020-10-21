package br.com.bootcamp.nossoBancoDigital.proposta.upload;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadDev implements Uploader {


	@Override
	public String upload(MultipartFile foto)  {
	
		return "http://aws.s3/dev/img14253.jog";

	}
}