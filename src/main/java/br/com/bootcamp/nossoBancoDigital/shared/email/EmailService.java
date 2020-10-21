package br.com.bootcamp.nossoBancoDigital.shared.email;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	public void enviarEmail(TemplateEmail template ) {
		template.criaTemplate() ;	
		// Chamar o resp por enviar o email em prod;
	}
	



}
