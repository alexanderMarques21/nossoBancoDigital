package br.com.bootcamp.nossoBancoDigital.proposta.controller.dto;

import br.com.bootcamp.nossoBancoDigital.proposta.model.Proposta;

public class PropostaDto {

	private ClienteDto cliente;
	private EnderecoDto endereco;
	private String urlFotoDocumento;

	public PropostaDto(Proposta proposta) {
		this.cliente = new ClienteDto(proposta.getCliente());
		this.endereco = new EnderecoDto(proposta.getEndereco());
		this.urlFotoDocumento = proposta.getUrlFotoDocumento();
	}

	public ClienteDto getCliente() {
		return cliente;
	}

	public EnderecoDto getEndereco() {
		return endereco;
	}

	public String getUrlFotoDocumento() {
		return urlFotoDocumento;
	}



}
