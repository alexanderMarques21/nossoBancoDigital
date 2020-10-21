package br.com.bootcamp.nossoBancoDigital.shared.email.templates;

import br.com.bootcamp.nossoBancoDigital.conta.controller.dto.ContaDto;
import br.com.bootcamp.nossoBancoDigital.shared.email.TemplateEmail;

public class TemplateCriarConta implements TemplateEmail{
	
	
	private ContaDto contaDto;
	
	public  TemplateCriarConta(ContaDto contaDto) {
		this.contaDto = contaDto;
	}
	


	@Override
	public String criaTemplate() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("<h2> Conta criada com sucesso </h2>");
		sb.append("<br/>");
		sb.append(contaDto.toString());
		
		System.out.println(sb.toString());
		
		return sb.toString();
	}
	
	

}
