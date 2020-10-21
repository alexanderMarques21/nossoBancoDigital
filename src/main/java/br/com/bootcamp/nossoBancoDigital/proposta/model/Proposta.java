package br.com.bootcamp.nossoBancoDigital.proposta.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import br.com.bootcamp.nossoBancoDigital.conta.model.Conta;
import br.com.bootcamp.nossoBancoDigital.proposta.model.enumerate.EnumStatusProposta;

@Entity
public class Proposta {


	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@OneToOne
	private Cliente cliente;

	@NotNull
	@OneToOne
	private Endereco endereco;

	@URL
	private String urlFotoDocumento;
	
	
	@OneToOne(mappedBy = "proposta")
	private Conta conta;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private EnumStatusProposta enumStatus;
	
	
	@Deprecated
	public Proposta() {}


	public  Proposta (Cliente cliente, Endereco endereco, EnumStatusProposta status) {
		this.cliente = cliente;
		this.endereco = endereco;
		this.enumStatus = status;
	
	
	}
	
	public Boolean isPropostaCompletaParaApresentacao() {
		if( cliente == null || endereco == null || urlFotoDocumento == null  ) {
			return false;
		}
		return true;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public String getUrlFotoDocumento() {
		return urlFotoDocumento;
	}


	public void setUrlFotoDocumento(String urlFotoDocumento) {
		this.urlFotoDocumento = urlFotoDocumento;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public EnumStatusProposta getEnumStatus() {
		return enumStatus;
	}

	public void setEnumStatus(EnumStatusProposta enumStatus) {
		this.enumStatus = enumStatus;
	}

	


	
	

}
