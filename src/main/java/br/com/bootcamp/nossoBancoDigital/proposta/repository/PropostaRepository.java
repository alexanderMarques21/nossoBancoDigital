package br.com.bootcamp.nossoBancoDigital.proposta.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bootcamp.nossoBancoDigital.proposta.model.Proposta;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
	
	Optional<Proposta > findById(UUID uuid);

}
