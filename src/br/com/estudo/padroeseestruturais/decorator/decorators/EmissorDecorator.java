package br.com.estudo.padroeseestruturais.decorator.decorators;

import br.com.estudo.padroeseestruturais.decorator.impl.Emissor;

public abstract class EmissorDecorator implements Emissor {

	private Emissor emissor;

	public EmissorDecorator(final Emissor emissor) {
		this.emissor = emissor;
	}

	public Emissor getEmissor() {
		return this.emissor;
	}

}
