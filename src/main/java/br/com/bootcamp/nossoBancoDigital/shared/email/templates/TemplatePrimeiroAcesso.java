package br.com.bootcamp.nossoBancoDigital.shared.email.templates;

import br.com.bootcamp.nossoBancoDigital.shared.email.TemplateEmail;

public class TemplatePrimeiroAcesso implements TemplateEmail {

	private String token;

	public TemplatePrimeiroAcesso(String token) {
		this.token = token;
	}

	@Override
	public String criaTemplate() {

		StringBuilder sb = new StringBuilder();
		sb.append("<h2> Token para acesso a conta </h2>");
		sb.append("<br/>");
		sb.append(token);

		System.out.println(sb.toString());

		return sb.toString();
	}

}
