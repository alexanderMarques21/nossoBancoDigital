package br.com.bootcamp.nossoBancoDigital.shared.email.templates;

import br.com.bootcamp.nossoBancoDigital.shared.email.TemplateEmail;

public class TemplatePropostaRecusada implements TemplateEmail {
	
	private String nomeCliente;
	
	public TemplatePropostaRecusada(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	@Override
	public String criaTemplate() {
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("<h2>"+nomeCliente +" nos de uma chance </h2>");
		sb.append("<br/>");
		sb.append("<p> Notamos que você recusou a nossa proposta, aqui no banco digital nossa prioridade e você. </p>");
		sb.append("<p> Contamos com inumeros beneficios para te auxiliar em sua vida financeira </p>");
		sb.append("<p> Vem ser banco digital </p>");

		System.out.println(sb.toString());
		return sb.toString();
	}


}
