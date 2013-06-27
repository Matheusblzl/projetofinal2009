package br.com.estudo.padroeseestruturais.composite.caminhos;

import java.util.ArrayList;
import java.util.List;

import br.com.estudo.padroeseestruturais.composite.impl.Trecho;

public class Caminho implements Trecho {

	private List<Trecho> trechos;

	public Caminho() {
		this.trechos = new ArrayList<Trecho>();
	}

	public void adiciona(final Trecho trecho) {
		this.trechos.add(trecho);
	}

	public void remove(final Trecho trecho) {
		this.trechos.remove(trecho);
	}

	@Override
	public void imprime() {
		for (Trecho trecho : this.trechos) {
			trecho.imprime();
		}
	}

}
