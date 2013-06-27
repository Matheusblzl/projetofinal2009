package br.com.estudo.padraocriacao.abstractFactory.impl;

import br.com.estudo.padraocriacao.abstractFactory.inter.Emissor;


public class EmissorMastercard implements Emissor {

	@Override
	public void enviar(String mensagem) {
		System.out.print("Enviando mensagem para a Mastercard: ");
		System.out.println(mensagem);
	}

}
