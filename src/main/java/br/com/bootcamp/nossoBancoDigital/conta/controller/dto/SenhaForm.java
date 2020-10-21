package br.com.bootcamp.nossoBancoDigital.conta.controller.dto;

import javax.validation.constraints.NotBlank;

import br.com.bootcamp.nossoBancoDigital.conta.controller.validation.ValidPassword;

public class SenhaForm {
	
	@ValidPassword
	private String senha;

	@NotBlank
	private String token;

	public String getSenha() {
		return senha;
	}

	public String getToken() {
		return token;
	}
	
	


	
	

}
