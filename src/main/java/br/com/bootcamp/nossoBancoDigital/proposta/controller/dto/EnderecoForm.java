package br.com.bootcamp.nossoBancoDigital.proposta.controller.dto;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import br.com.bootcamp.nossoBancoDigital.configuration.validation.NotFoundException;
import br.com.bootcamp.nossoBancoDigital.proposta.model.Cliente;
import br.com.bootcamp.nossoBancoDigital.proposta.model.Endereco;
import br.com.bootcamp.nossoBancoDigital.proposta.repository.ClienteRepository;

public class EnderecoForm {


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


	public Endereco novoEndereco(Long idCliente, ClienteRepository clienteRepository) {
		Optional<Cliente> optionalCliente = clienteRepository.findById(idCliente);

		Endereco endereco = optionalCliente.map(cliente -> {
			return new Endereco(cep, rua, bairro, complemento, cidade, estado, cliente);
		}).orElseThrow(() -> new NotFoundException("Não há cliente com o id informado"));

		return endereco;
	}
	

	public String getCep() {
		return cep;
	}

	public String getRua() {
		return rua;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}

}
