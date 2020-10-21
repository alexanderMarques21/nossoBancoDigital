package br.com.bootcamp.nossoBancoDigital.conta.controller;

import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bootcamp.nossoBancoDigital.configuration.validation.NotFoundException;
import br.com.bootcamp.nossoBancoDigital.conta.controller.dto.PrimeiroAcessoForm;
import br.com.bootcamp.nossoBancoDigital.conta.controller.dto.SenhaForm;
import br.com.bootcamp.nossoBancoDigital.conta.controller.dto.TransferenciaForm;
import br.com.bootcamp.nossoBancoDigital.conta.model.Conta;
import br.com.bootcamp.nossoBancoDigital.conta.model.Token;
import br.com.bootcamp.nossoBancoDigital.conta.model.Transferencia;
import br.com.bootcamp.nossoBancoDigital.conta.repository.ContaRepository;
import br.com.bootcamp.nossoBancoDigital.conta.repository.TokenRepository;
import br.com.bootcamp.nossoBancoDigital.conta.service.ContaService;
import br.com.bootcamp.nossoBancoDigital.shared.email.EmailService;
import br.com.bootcamp.nossoBancoDigital.shared.email.templates.TemplatePrimeiroAcesso;


@RestController
@RequestMapping("/conta")
public class ContaController {
	
	@Autowired
	ContaRepository contaRepository;
	
	@Autowired
	TokenRepository tokenRepository;
	
	@Autowired
	ContaService contaService;
	
	@Autowired
	EmailService emailService;
	
	
	@PostMapping("/primeiroAcesso")
	@Transactional
	public ResponseEntity<?> primeiroAcessoConta(@RequestBody @Valid PrimeiroAcessoForm form) {
		String token = contaService.gerarTokenPrimeiroAcesso(form.getCpf(), form.getEmail());
		emailService.enviarEmail(new TemplatePrimeiroAcesso(token));
		return ResponseEntity.ok().build();
	}

	
	
	@PostMapping("/criarSenha")
	@Transactional
	public ResponseEntity<?> criarSenha(@RequestBody @Valid SenhaForm form) {
		
		Token token = tokenRepository.findByChave(form.getToken())
				.orElseThrow(()->new NotFoundException("Token não encontrado"));
	
		if(contaService.isTokenValido(token)) {
			Conta conta = token.getConta();
			conta.setSenha(form.getSenha());
			contaRepository.save(conta);
		}else {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok().build();
	}
	
	
	@PostMapping("/transferencia")
	@Transactional
	@Async
	public CompletableFuture<?> transferencia(@RequestBody @Valid TransferenciaForm form){
		
		Transferencia transferencia = form.novaTransferencia();
		contaService.recebeTransferencia(transferencia);
		
		return  CompletableFuture.completedFuture("Result is ready!");
		
	}
	

}
