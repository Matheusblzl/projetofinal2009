package br.com.estudo.padroeseestruturais.decorator.emissor;

import br.com.estudo.padroeseestruturais.decorator.impl.Emissor;

public class EmissorBasico implements Emissor {

	@Override
    public void enviar(final String mensagem) {
		System.out.println(" Enviando uma mensagem : ");
		System.out.println(mensagem);
	}
	
}
