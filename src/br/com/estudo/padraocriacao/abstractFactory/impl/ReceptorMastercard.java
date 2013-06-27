package br.com.estudo.padraocriacao.abstractFactory.impl;

import br.com.estudo.padraocriacao.abstractFactory.inter.Receptor;

public class ReceptorMastercard implements Receptor {

	@Override
	public String receber() {
		System.out.println("Recebendo mensagem da Mastercard");
		return "Mensagem da Mastercard.";
	}

}
