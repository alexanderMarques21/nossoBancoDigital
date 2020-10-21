package br.com.bootcamp.nossoBancoDigital.conta.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.bootcamp.nossoBancoDigital.proposta.model.Proposta;

@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Length(min = 4, max = 4)
	private String agencia = gerarValoresAleatorios(4);

	@Length(min = 8, max = 8)
	private String conta = gerarValoresAleatorios(8);

	@Length(min = 3, max = 3)
	private String codigoBanco = "777";

	private BigDecimal saldo = new BigDecimal(0);

	
	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(unique = true, name = "proposta_id")
	private Proposta proposta;

	@OneToOne(mappedBy = "conta", fetch = FetchType.LAZY)
	private Token token;

	@NotNull
	private String senha;

	@Version
	private Long version;

	@Deprecated
	public Conta() {}
	
	public Conta(@NotNull Proposta proposta) {
		this.proposta = proposta;
	}

	private String encodaSenha(String senha) {

		return new BCryptPasswordEncoder().encode(senha);
	}

	public void creditar(BigDecimal credito) {
		this.saldo = saldo.add(credito);
	}

	private String gerarValoresAleatorios(int quantidade) {
		return RandomStringUtils.randomNumeric(quantidade);
	}

	
	
	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public Proposta getProposta() {
		return proposta;
	}

	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = encodaSenha(senha);
	}

}
