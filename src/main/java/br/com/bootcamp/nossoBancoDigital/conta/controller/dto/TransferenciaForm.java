package br.com.bootcamp.nossoBancoDigital.conta.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import br.com.bootcamp.nossoBancoDigital.conta.model.Transferencia;

public class TransferenciaForm {

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
	
	
	
	public Transferencia novaTransferencia() {
		
		return new Transferencia(
				valorTransferencia, dataRealizacao,
				documentoIdentificador,bancoOrigem, 
				contaOrigem, agenciaOrigem, 
				codigoUnicoTransferencia,
				contaDestino, agenciaDestino
				);
	}
	
	
	
	public BigDecimal getValorTransferencia() {
		return valorTransferencia;
	}
	public LocalDateTime getDataRealizacao() {
		return dataRealizacao;
	}
	public String getDocumentoIdentificador() {
		return documentoIdentificador;
	}
	public String getBancoOrigem() {
		return bancoOrigem;
	}
	public String getContaOrigem() {
		return contaOrigem;
	}
	public String getAgenciaOrigem() {
		return agenciaOrigem;
	}
	public String getCodigoUnicoTransferencia() {
		return codigoUnicoTransferencia;
	}
	public String getContaDestino() {
		return contaDestino;
	}
	public String getAgenciaDestino() {
		return agenciaDestino;
	}
	
	
	
	
	
	
}
