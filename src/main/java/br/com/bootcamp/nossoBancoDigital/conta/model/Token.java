package br.com.bootcamp.nossoBancoDigital.conta.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.RandomStringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;


@Entity
public class Token {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotBlank
	private String chave = gerarChave();
	
	@NotNull
	private LocalDateTime dataCriacao = LocalDateTime.now();
	
	@NotNull
	@JsonFormat (shape = Shape.STRING , pattern = "dd/MM/yyyy kk:mm")
	@Future
	private LocalDateTime dataExpiracao;
	
	@NotNull
	private Boolean utilizado = Boolean.FALSE; 
	
	@OneToOne
	@JoinColumn(unique = true, name = "conta_id")
	private Conta conta;
	
	@Deprecated
	public Token() {}
	
	
	public Token(Long tempoExpiracao) {
		dataExpiracao = dataCriacao.plusMinutes(tempoExpiracao);
	}
	
	private String gerarChave() {
		return RandomStringUtils.randomAlphabetic(8);
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getChave() {
		return chave;
	}


	public void setChave(String chave) {
		this.chave = chave;
	}


	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}


	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}


	public LocalDateTime getDataExpiracao() {
		return dataExpiracao;
	}


	public void setDataExpiracao(LocalDateTime dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}


	public Boolean getUtilizado() {
		return utilizado;
	}


	public void setUtilizado(Boolean utilizado) {
		this.utilizado = utilizado;
	}


	public Conta getConta() {
		return conta;
	}


	public void setConta(Conta conta) {
		this.conta = conta;
	}



	

	
	

}
