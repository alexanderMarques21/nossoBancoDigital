package br.com.bootcamp.nossoBancoDigital.conta.service;

import java.time.LocalDateTime;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bootcamp.nossoBancoDigital.configuration.validation.NotFoundException;
import br.com.bootcamp.nossoBancoDigital.conta.controller.dto.ContaDto;
import br.com.bootcamp.nossoBancoDigital.conta.model.Conta;
import br.com.bootcamp.nossoBancoDigital.conta.model.Token;
import br.com.bootcamp.nossoBancoDigital.conta.model.Transferencia;
import br.com.bootcamp.nossoBancoDigital.conta.repository.ContaRepository;
import br.com.bootcamp.nossoBancoDigital.conta.repository.TokenRepository;
import br.com.bootcamp.nossoBancoDigital.proposta.model.Proposta;
import br.com.bootcamp.nossoBancoDigital.proposta.model.enumerate.EnumStatusProposta;
import br.com.bootcamp.nossoBancoDigital.proposta.repository.PropostaRepository;
import br.com.bootcamp.nossoBancoDigital.shared.email.EmailService;
import br.com.bootcamp.nossoBancoDigital.shared.email.templates.TemplateCriarConta;

@Service
public class ContaService {

	@Autowired
	TokenRepository tokenRepository;

	@Autowired
	ContaRepository contaRepository;

	@Autowired
	PropostaRepository propostaRepository;

	@Autowired
	TransferenciaService transferenciaService;

	@Autowired
	SaldoService saldoService;

	@Value("${tempoExpiracao}")
	private Long tempoExpiracao;

	@Autowired
	EmailService emailService;

	@Autowired
	ValidaDocumentoService validaDocumentoService;
	
	
	

	public void validarDocumento(Proposta proposta) {
		try {
			validaDocumentoService.validaCall(proposta);
		}catch(Exception e ) {
		
		}
	}
	
	
	@Transactional
	public void criarConta(Proposta proposta) {
		Conta conta = new Conta(proposta);

		String senhaDefault = RandomStringUtils.randomAlphanumeric(8);

		conta.setSenha(senhaDefault);
		contaRepository.save(conta);
		emailService.enviarEmail(new TemplateCriarConta(new ContaDto(conta)));
		proposta.setEnumStatus(EnumStatusProposta.ACEITA);
		propostaRepository.save(proposta);

	}
	
	@Transactional
	public void falhaAoValidarDocumento(Proposta proposta) {
		proposta.setEnumStatus(EnumStatusProposta.PENDENTE);
		propostaRepository.save(proposta);
	}
	


	public String gerarTokenPrimeiroAcesso(String cpf, String email) {
		Token token = new Token(tempoExpiracao);
		Conta conta = contaRepository.findByCpfAndEmail(email, cpf).orElseThrow(
				() -> new NotFoundException("Não foi encontrado uma conta associada aos dados informados"));
		token.setConta(conta);
		tokenRepository.save(token);

		return token.getChave();
	}

	public void recebeTransferencia(Transferencia transferencia) {
		try {

			if (transferenciaService.registrarNovaTransferencia(transferencia)) {
				saldoService.atualizaSaldo(transferencia.getAgenciaDestino(), transferencia.getContaDestino(),
						transferencia.getValorTransferencia());
			}

		} catch (ObjectOptimisticLockingFailureException e) {
			saldoService.atualizaSaldo(transferencia.getAgenciaDestino(), transferencia.getContaDestino(),
					transferencia.getValorTransferencia());
		}

	}



	public Boolean isTokenValido(Token token) {

		if (token.getUtilizado()) {
			return false;
		}

		if (token.getDataExpiracao().isBefore(LocalDateTime.now())) {
			return false;
		}
		token.setUtilizado(true);

		return true;

	}

}
