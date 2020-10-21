package br.com.bootcamp.nossoBancoDigital.proposta.controller.dto;

import java.time.LocalDate;

import br.com.bootcamp.nossoBancoDigital.proposta.model.Cliente;

public class ClienteDto {

	private String nome;

	private String sobrenome;

	private String email;

	private String cpf;

	private LocalDate dataNascimento;
	
	public ClienteDto(Cliente cliente) {
		
		this.nome = cliente.getNome();
		this.sobrenome = cliente.getSobrenome();
		this.email = cliente.getEmail();
		this.cpf = cliente.getCpf();
		this.dataNascimento = cliente.getDataNascimento();
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	

}
