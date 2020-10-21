package br.com.bootcamp.nossoBancoDigital.conta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bootcamp.nossoBancoDigital.conta.model.Token;

public interface  TokenRepository extends JpaRepository<Token, Long>  {

	Optional<Token> findByChave(String token);

}
