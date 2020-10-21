package br.com.bootcamp.nossoBancoDigital.proposta.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bootcamp.nossoBancoDigital.proposta.controller.dto.ClienteForm;
import br.com.bootcamp.nossoBancoDigital.proposta.controller.dto.EnderecoForm;
import br.com.bootcamp.nossoBancoDigital.proposta.controller.validation.ClienteUnicoValidator;
import br.com.bootcamp.nossoBancoDigital.proposta.model.Cliente;
import br.com.bootcamp.nossoBancoDigital.proposta.model.Endereco;
import br.com.bootcamp.nossoBancoDigital.proposta.model.Proposta;
import br.com.bootcamp.nossoBancoDigital.proposta.model.enumerate.EnumStatusProposta;
import br.com.bootcamp.nossoBancoDigital.proposta.repository.ClienteRepository;
import br.com.bootcamp.nossoBancoDigital.proposta.repository.EnderecoRepository;
import br.com.bootcamp.nossoBancoDigital.proposta.repository.PropostaRepository;



@RestController
@RequestMapping("/proposta/cadastro")
public class ClienteController {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	PropostaRepository propostaRepository;

	
	
	@InitBinder("clienteForm")
	public void init(WebDataBinder dataBinder) {
		dataBinder.addValidators(new ClienteUnicoValidator(clienteRepository));
	}
	
	
	
	@PostMapping("/cliente")
	@Transactional
	public ResponseEntity<?> cadastrarCliente(@Valid @RequestBody ClienteForm  formCliente, UriComponentsBuilder uriBuilder){
		
		Cliente cliente = formCliente.novoCliente();
		clienteRepository.save(cliente);
	
		URI uri  = uriBuilder.path("/proposta/cadastro/{id}/endereco")
				.buildAndExpand(cliente.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();	
				
	}
	
	
	@PostMapping("/{idCliente}/endereco")
	@Transactional
	public ResponseEntity<?> cadastrarEndereco( @PathVariable Long idCliente, @Valid @RequestBody EnderecoForm formEndereco, UriComponentsBuilder uriBuilder){
		
		Endereco endereco = formEndereco.novoEndereco(idCliente,clienteRepository);
		
		enderecoRepository.save(endereco);
		Cliente cliente = endereco.getCliente();
		
		Proposta proposta = new Proposta(cliente, endereco, EnumStatusProposta.INICIADA);
		propostaRepository.save(proposta);
		
	
		URI uri  = uriBuilder.path("/proposta/{idProposta}/documento/upload")
				.buildAndExpand(proposta.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();	
			
	}
	


}
