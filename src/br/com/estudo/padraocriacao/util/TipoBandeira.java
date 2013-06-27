package br.com.estudo.padraocriacao.util;

public enum TipoBandeira {

	VISA("VISA"),MASTER("Mastercard");
	
	private TipoBandeira(final String tipo) {
		this.tipo = tipo;
	}
	
	private String tipo;
	
	public String getTipo() {
		return this.tipo;
	}
}
