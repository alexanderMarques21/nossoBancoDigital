package br.com.bootcamp.nossoBancoDigital.proposta.controller.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import br.com.bootcamp.nossoBancoDigital.conta.controller.dto.DocumentoUploadForm;

@Component
public class FileValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return DocumentoUploadForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		DocumentoUploadForm documentoUploadForm = (DocumentoUploadForm) target;

		MultipartFile multipartFile = documentoUploadForm.getDocumento();
		
		if(multipartFile == null || multipartFile.isEmpty()) {
			errors.rejectValue("documento", null, "A imagem do documento é obrigatoria");
		}

	}

	
}
