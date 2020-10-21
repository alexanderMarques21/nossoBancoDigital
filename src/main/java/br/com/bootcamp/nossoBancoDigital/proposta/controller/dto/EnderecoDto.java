package br.com.bootcamp.nossoBancoDigital.proposta.controller.dto;

import br.com.bootcamp.nossoBancoDigital.proposta.model.Endereco;

public class EnderecoDto {

	private String cep;
	private String rua;
	private String bairro;
	private String complemento;
	private String cidade;
	private String estado;
	
	
	
	public EnderecoDto(Endereco endereco) {
		this.cep = endereco.getCep();
		this.rua = endereco.getRua();
		this.bairro = endereco.getBairro();
		this.complemento = endereco.getComplemento();
		this.cidade = endereco.getCidade();
		this.estado = endereco.getEstado();
	}



	public String getCep() {
		return cep;
	}



	public String getRua() {
		return rua;
	}



	public String getBairro() {
		return bairro;
	}



	public String getComplemento() {
		return complemento;
	}



	public String getCidade() {
		return cidade;
	}



	public String getEstado() {
		return estado;
	}
	
	

}
