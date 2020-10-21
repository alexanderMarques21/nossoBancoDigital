package br.com.bootcamp.nossoBancoDigital.proposta.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bootcamp.nossoBancoDigital.configuration.validation.NotFoundException;
import br.com.bootcamp.nossoBancoDigital.conta.controller.dto.DocumentoUploadForm;
import br.com.bootcamp.nossoBancoDigital.conta.service.ContaService;
import br.com.bootcamp.nossoBancoDigital.proposta.controller.dto.PropostaDto;
import br.com.bootcamp.nossoBancoDigital.proposta.controller.validation.FileValidator;
import br.com.bootcamp.nossoBancoDigital.proposta.model.Proposta;
import br.com.bootcamp.nossoBancoDigital.proposta.model.enumerate.EnumStatusProposta;
import br.com.bootcamp.nossoBancoDigital.proposta.repository.PropostaRepository;
import br.com.bootcamp.nossoBancoDigital.proposta.upload.UploadDev;
import br.com.bootcamp.nossoBancoDigital.shared.email.EmailService;
import br.com.bootcamp.nossoBancoDigital.shared.email.templates.TemplatePropostaRecusada;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

	@Autowired
	PropostaRepository propostaRepository;
	
	
	@Autowired
	FileValidator fileValidator;

	@Autowired
	ContaService contaService;
	
	@Autowired
	EmailService emailService;


	@PostMapping("/{idProposta}/documento/upload")
	@Transactional
	public ResponseEntity<?> uploadFotoCpf(@PathVariable Long idProposta, @ModelAttribute("documento") DocumentoUploadForm docUploadForm, BindingResult result,
			UriComponentsBuilder uriBuilder) throws MethodArgumentNotValidException {

		Proposta proposta = propostaRepository.findById(idProposta)
				.orElseThrow(() -> new NotFoundException("Não foi encontrada proposta associada ao id informado"));
	
		fileValidator.validate(docUploadForm, result);
		if(result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
		}
		
		String urlFotoDocumento = docUploadForm.uploadFoto(new UploadDev());

		proposta.setUrlFotoDocumento(urlFotoDocumento);
		propostaRepository.save(proposta);

		URI uri = uriBuilder.path("/proposta/{idProposta}/").buildAndExpand(idProposta).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{idProposta}")
	public ResponseEntity<?> exibirProposta(@PathVariable Long idProposta) {

		Optional<Proposta> propostaOptional = propostaRepository.findById(idProposta);
		if (propostaOptional.isPresent()) {
			Proposta proposta = propostaOptional.get();
			if (!proposta.isPropostaCompletaParaApresentacao()) {
				return ResponseEntity.unprocessableEntity().build();
			}
			PropostaDto propostaDto = new PropostaDto(proposta);
			return ResponseEntity.ok(propostaDto);
		}

		throw new NotFoundException("Não foi encontrada proposta associada ao id informado");
	}

	@GetMapping("/{idProposta}/confirmacaoProposta")
	public ResponseEntity<?> confirmacaoProposta(@PathVariable Long idProposta, @RequestParam String resposta) {

		Optional<Proposta> optionalProposta = propostaRepository.findById(idProposta);
		Proposta proposta = optionalProposta
				.orElseThrow(() -> new NotFoundException("Não foi encontrada proposta associada ao id informado"));

		if (resposta.equalsIgnoreCase("ACEITO")) {
			contaService.validarDocumento(proposta);
		} else {
			proposta.setEnumStatus(EnumStatusProposta.RECUSADA);
			emailService.enviarEmail(new TemplatePropostaRecusada(proposta.getCliente().getNome()));
			
		}

		return ResponseEntity.ok().build();
	}
	
	
	


}
