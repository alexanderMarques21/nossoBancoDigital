package br.com.bootcamp.nossoBancoDigital.conta.controller.dto;

import javax.validation.constraints.NotBlank;

public class PrimeiroAcessoForm {
	
	
	@NotBlank
	private String email;
	@NotBlank
	private String cpf;
	
	
	public PrimeiroAcessoForm(String email, String cpf) {
		this.email = email;
		this.cpf = cpf;
	}


	public String getEmail() {
		return email;
	}


	public String getCpf() {
		return cpf;
	}
	
	

}
