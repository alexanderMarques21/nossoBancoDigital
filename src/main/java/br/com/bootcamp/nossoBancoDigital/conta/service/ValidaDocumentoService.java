package br.com.bootcamp.nossoBancoDigital.conta.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.bootcamp.nossoBancoDigital.proposta.model.Proposta;

@Service
public class ValidaDocumentoService {

	@Lazy
	@Autowired
	private ContaService contaService;

	
	@Retryable(value = RuntimeException.class, maxAttempts = 3)
	@Async
	public void validaCall(Proposta proposta) throws Exception {

		String simulacao = RandomStringUtils.random(1, false, true);
		System.out.println("tentou");
		if (Integer.parseInt(simulacao) % 2 == 0) {
			throw new RuntimeException();
		}
		
		contaService.criarConta(proposta);
	

	}

	@Recover
	public void recover(RuntimeException e, Proposta proposta) {
		contaService.falhaAoValidarDocumento(proposta);
	}

}
