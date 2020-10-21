package br.com.bootcamp.nossoBancoDigital.conta.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bootcamp.nossoBancoDigital.conta.model.Transferencia;
import br.com.bootcamp.nossoBancoDigital.conta.repository.TransferenciaRepository;

@Service
public class TransferenciaService {
	
	@Autowired
	TransferenciaRepository transferenciaRepository;
	
	
	public Boolean registrarNovaTransferencia(Transferencia transferencia) {
		
		Optional<Transferencia> transferenciaOptional = transferenciaRepository.findByCodigoUnicoTransferencia(transferencia.getCodigoUnicoTransferencia());
		
		if(transferenciaOptional.isPresent()) {
			System.out.println("A transferencia com identificador: "+ transferencia.getCodigoUnicoTransferencia()+" já foi registrada");
			return false;
		}
		transferenciaRepository.save(transferencia);
		return true;
		
	}

}
