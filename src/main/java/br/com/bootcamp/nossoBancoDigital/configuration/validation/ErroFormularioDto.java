package br.com.bootcamp.nossoBancoDigital.configuration.validation;

import org.springframework.util.StringUtils;

public class ErroFormularioDto {

	private String campo;
	private String mensagem;

	public ErroFormularioDto(String campo, String mensagem) {
		this.campo = campo;
		this.mensagem = mensagem;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return StringUtils.capitalize(mensagem);
	}

}
