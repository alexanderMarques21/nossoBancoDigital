package br.com.bootcamp.nossoBancoDigital.proposta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bootcamp.nossoBancoDigital.proposta.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
