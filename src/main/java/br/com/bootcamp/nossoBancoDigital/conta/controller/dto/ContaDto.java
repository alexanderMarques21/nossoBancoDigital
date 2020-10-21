package br.com.bootcamp.nossoBancoDigital.conta.controller.dto;

import br.com.bootcamp.nossoBancoDigital.conta.model.Conta;

public class ContaDto {
	
	

	private String agencia;
	private String conta;
	private String codigoBanco;
	
	
	public ContaDto(Conta conta) {
		this.agencia = conta.getAgencia();
		this.conta = conta.getConta();
		this.codigoBanco = conta.getCodigoBanco();
	}


	public String getAgencia() {
		return agencia;
	}


	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}


	public String getConta() {
		return conta;
	}


	public void setConta(String conta) {
		this.conta = conta;
	}


	public String getCodigoBanco() {
		return codigoBanco;
	}


	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}


	@Override
	public String toString() {
		return "\n Agencia=" + agencia + " \n Conta=" + conta + " \n CodigoBanco=" + codigoBanco ;
	}
	
	




}
