package br.com.bootcamp.nossoBancoDigital.conta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bootcamp.nossoBancoDigital.conta.model.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long>{

	Optional<Transferencia> findByCodigoUnicoTransferencia(String codigoUnicoTransferencia);

}
