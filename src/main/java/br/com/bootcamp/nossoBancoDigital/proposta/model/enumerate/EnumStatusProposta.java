package br.com.bootcamp.nossoBancoDigital.proposta.model.enumerate;

public enum EnumStatusProposta {
	
	INICIADA(1),
	ACEITA(2) ,
	RECUSADA(3),
	PENDENTE(4);
	private int valorProposta;
	
	private EnumStatusProposta(int valorProposta) {
		this.valorProposta = valorProposta;
	}

	public int getValorProposta() {
		return valorProposta;
	}
	
	
}
