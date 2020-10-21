package br.com.bootcamp.nossoBancoDigital.proposta.controller.validation;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.bootcamp.nossoBancoDigital.configuration.validation.ErroFormularioDto;
import br.com.bootcamp.nossoBancoDigital.proposta.controller.dto.ClienteForm;
import br.com.bootcamp.nossoBancoDigital.proposta.model.Cliente;
import br.com.bootcamp.nossoBancoDigital.proposta.repository.ClienteRepository;

public class ClienteUnicoValidator extends CampoUnicoValidator<ClienteForm>{
	
	private ClienteRepository clienteRepository;
	private List<ErroFormularioDto> erros = new ArrayList<>();
	
	public ClienteUnicoValidator(ClienteRepository clienteRepository) {
		super(ClienteForm.class);
		this.clienteRepository = clienteRepository;
	}

	@Override
	protected List<ErroFormularioDto> getNomesCamposInvalidos() {
		return erros;
	}


	@Override
	protected void buscaPorCampo(Object form) {
		ClienteForm clienteForm = (ClienteForm) form;
		verificaCampos(clienteForm);

	}
	
	
	private void verificaCampos(ClienteForm clienteForm) {
		
		Optional<Cliente> possivelClientePeloCpf = clienteRepository.findByCpf(clienteForm.getCpf());
		Optional<Cliente> possivelClientePeloEmail = clienteRepository.findByEmail(clienteForm.getEmail());
		
		if(possivelClientePeloCpf.isPresent()) {
			erros.add(new ErroFormularioDto("cpf", "Já existe um registro salvo com o cpf informado"));
		}
		
		if(possivelClientePeloEmail.isPresent()) {
			erros.add(new ErroFormularioDto("email", "Já existe um registro salvo com o email informado"));
		}
		
		int idade = Period.between(clienteForm.getDataNascimento(), LocalDate.now()).getYears();
		
		if(idade <18) {
			erros.add(new ErroFormularioDto("dataNascimento", "Idade mínima é 18 anos"));
		}
		
	}
	
	

	

}
