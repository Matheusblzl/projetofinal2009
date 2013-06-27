package br.com.estudo.padraocriacao.abstractFactory.util;

import br.com.estudo.padraocriacao.abstractFactory.impl.EmissorMastercard;
import br.com.estudo.padraocriacao.abstractFactory.impl.EmissorVisa;
import br.com.estudo.padraocriacao.abstractFactory.inter.Emissor;
import br.com.estudo.padraocriacao.util.TipoBandeira;

public class EmissorCreator {

	public Emissor create(final TipoBandeira bandeira) {
		if (TipoBandeira.VISA == bandeira) {
			return new EmissorVisa();
		} else if (TipoBandeira.MASTER == bandeira) {
			return new EmissorMastercard();
		} else {
			throw new IllegalArgumentException("Tipo de emissor não suportado.");
		}
	}
	
}
