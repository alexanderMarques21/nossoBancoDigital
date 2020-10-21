package br.com.bootcamp.nossoBancoDigital.conta.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bootcamp.nossoBancoDigital.configuration.validation.NotFoundException;
import br.com.bootcamp.nossoBancoDigital.conta.model.Conta;
import br.com.bootcamp.nossoBancoDigital.conta.repository.ContaRepository;

@Service
public class SaldoService {
	
	
	private final ContaRepository contaRepository;
	
	
	public SaldoService(ContaRepository contaRepository) {
		this.contaRepository = contaRepository;
	}
	

	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void atualizaSaldo(String nuAgencia, String nuConta, BigDecimal valor) {
		Conta conta = contaRepository.findByAgenciaAndConta(nuAgencia, nuConta)
		.orElseThrow(() -> new NotFoundException("Não foi encontrado uma conta para receber esta transferencia"));	

		conta.creditar(valor);
	}
	

	
	
}
