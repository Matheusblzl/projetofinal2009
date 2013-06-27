package br.com.estudo.padraocriacao.abstractFactory.impl;

import br.com.estudo.padraocriacao.abstractFactory.inter.Emissor;

public class EmissorVisa implements Emissor {

	@Override
	public void enviar(String mensagem) {
		System.out.print("Enviando mensagem para os VISA: ");
		System.out.println(mensagem);
	}

}
