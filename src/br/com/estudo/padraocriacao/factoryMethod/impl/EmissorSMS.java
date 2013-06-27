package br.com.estudo.padraocriacao.factoryMethod.impl;

import br.com.estudo.padraocriacao.factoryMethod.Emissor;

public class EmissorSMS implements Emissor {

	@Override
	public void enviar(String mensagem) {
		System.out.println("Enviando por SMS a mensagem: " + mensagem);
	}

}
