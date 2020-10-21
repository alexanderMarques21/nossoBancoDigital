package br.com.bootcamp.nossoBancoDigital.proposta.controller.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.bootcamp.nossoBancoDigital.proposta.model.Cliente;

public class ClienteForm{
	
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String sobrenome;

	@NotBlank
	@Email
	private String email;
	
	@CPF
	private String cpf;

	@NotNull
	@Past
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	

	public Cliente novoCliente() {
		return new Cliente(nome,sobrenome, email, dataNascimento, cpf);
	}

	public String getNome() {
		return nome;
	}

	
	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}
	
	public String getEmail() {
		return email;
	}

}
