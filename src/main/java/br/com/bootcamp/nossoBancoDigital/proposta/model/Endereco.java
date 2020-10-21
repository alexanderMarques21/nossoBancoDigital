package br.com.bootcamp.nossoBancoDigital.proposta.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "ENDERECO_ÚNICO_POR_CLIENTE", columnNames = { "cliente_id" }) })
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@Pattern(regexp = "[0-9]{5}-[0-9]{3}")
	private String cep;

	@NotBlank
	private String rua;
	
	@NotBlank
	private String bairro;
	
	@NotBlank
	private String complemento;

	@NotBlank
	private String cidade;

	@NotBlank
	private String estado;

	@OneToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	

	public Endereco(@NotBlank String cep, @NotBlank String rua, @NotBlank String bairro, @NotBlank String complemento,
			@NotBlank String cidade, @NotBlank String estado, Cliente cliente) {
		this.cep = cep;
		this.rua = rua;
		this.bairro = bairro;
		this.complemento = complemento;
		this.cidade = cidade;
		this.estado = estado;
		this.cliente = cliente;
	}



	@Deprecated
	public Endereco() {
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public String getComplemento() {
		return complemento;
	}



	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	
	
}
