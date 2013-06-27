package br.com.estudo.padroescomportamentais.state.model;

import br.com.estudo.padroescomportamentais.state.inter.Bandeira;

public class Taximetro {

	private Bandeira bandeira;

	public Taximetro(final Bandeira bandeira) {
		this.bandeira = bandeira;
	}

	public void setBandeira(final Bandeira bandeira) {
		this.bandeira = bandeira;
	}

	public double calculaValorDaCorrida(final double tempo, final double distancia) {
		return this.bandeira.calculaValorDaCorrida(tempo, distancia);
	}

}
