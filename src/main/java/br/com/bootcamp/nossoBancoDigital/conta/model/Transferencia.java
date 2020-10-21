package br.com.bootcamp.nossoBancoDigital.conta.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Transferencia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
		
	@NotNull
	private BigDecimal valorTransferencia;
	
	@NotNull
	private LocalDateTime dataRealizacao;
	
	@NotNull
	private String documentoIdentificador;
	
	@NotNull
	private String bancoOrigem;
	
	@NotNull
	private String contaOrigem;
	
	@NotNull
	private String agenciaOrigem;
	
	@NotNull
	private String codigoUnicoTransferencia;
	
	@NotNull
	private String contaDestino;
	
	@NotNull
	private String agenciaDestino;
	
	
	@Deprecated
	public Transferencia() {}

	
	

	public Transferencia(@NotBlank BigDecimal valorTransferencia, @NotNull LocalDateTime dataRealizacao,
			@NotNull String documentoIdentificador, @NotNull String bancoOrigem, @NotNull String contaOrigem,
			@NotNull String agenciaOrigem, @NotNull String codigoUnicoTransferencia, @NotNull String contaDestino,
			@NotNull String agenciaDestino) {

		this.valorTransferencia = valorTransferencia;
		this.dataRealizacao = dataRealizacao;
		this.documentoIdentificador = documentoIdentificador;
		this.bancoOrigem = bancoOrigem;
		this.contaOrigem = contaOrigem;
		this.agenciaOrigem = agenciaOrigem;
		this.codigoUnicoTransferencia = codigoUnicoTransferencia;
		this.contaDestino = contaDestino;
		this.agenciaDestino = agenciaDestino;
	}







	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorTransferencia() {
		return valorTransferencia;
	}

	public void setValorTransferencia(BigDecimal valorTransferencia) {
		this.valorTransferencia = valorTransferencia;
	}

	public LocalDateTime getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(LocalDateTime dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public String getDocumentoIdentificador() {
		return documentoIdentificador;
	}

	public void setDocumentoIdentificador(String documentoIdentificador) {
		this.documentoIdentificador = documentoIdentificador;
	}

	public String getBancoOrigem() {
		return bancoOrigem;
	}

	public void setBancoOrigem(String bancoOrigem) {
		this.bancoOrigem = bancoOrigem;
	}

	public String getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(String contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public String getAgenciaOrigem() {
		return agenciaOrigem;
	}

	public void setAgenciaOrigem(String agenciaOrigem) {
		this.agenciaOrigem = agenciaOrigem;
	}

	public String getCodigoUnicoTransferencia() {
		return codigoUnicoTransferencia;
	}

	public void setCodigoUnicoTransferencia(String codigoUnicoTransferencia) {
		this.codigoUnicoTransferencia = codigoUnicoTransferencia;
	}

	public String getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}

	public String getAgenciaDestino() {
		return agenciaDestino;
	}

	public void setAgenciaDestino(String agenciaDestino) {
		this.agenciaDestino = agenciaDestino;
	}
	
	
	
	

}
