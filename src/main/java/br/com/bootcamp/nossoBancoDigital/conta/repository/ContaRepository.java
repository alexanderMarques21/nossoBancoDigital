package br.com.bootcamp.nossoBancoDigital.conta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.bootcamp.nossoBancoDigital.conta.model.Conta;

public interface ContaRepository extends JpaRepository<Conta,Long> {
	
	
	Optional<Conta> findByAgenciaAndConta(String agencia, String conta);
	
	@Query("SELECT c from Conta c WHERE c.proposta.cliente.email = :pEmail AND c.proposta.cliente.cpf = :pCpf ")
	Optional<Conta> findByCpfAndEmail(@Param(value = "pEmail") String email ,@Param(value = "pCpf") String cpf );
	
	

}
