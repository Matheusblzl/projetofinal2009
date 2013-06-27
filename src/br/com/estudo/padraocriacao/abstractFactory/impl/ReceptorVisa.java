package br.com.estudo.padraocriacao.abstractFactory.impl;

import br.com.estudo.padraocriacao.abstractFactory.inter.Receptor;

public class ReceptorVisa implements Receptor {

	@Override
	public String receber() {
		System.out.println("Recebendo mensagem do VISA...");
		return "Mensagem do VISA.";
	}

}
