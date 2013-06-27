package br.com.estudo.padraocriacao.factoryMethod.impl;

import br.com.estudo.padraocriacao.factoryMethod.Emissor;


public class EmissorJMS implements Emissor {

	@Override
	public void enviar(String mensagem) {
		System.out.println("Enviando por JMS a mensagem: " + mensagem);
	}

}
