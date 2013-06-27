package br.com.estudo.padraocriacao.abstractFactory.util;

import br.com.estudo.padraocriacao.abstractFactory.impl.ReceptorMastercard;
import br.com.estudo.padraocriacao.abstractFactory.impl.ReceptorVisa;
import br.com.estudo.padraocriacao.abstractFactory.inter.Receptor;
import br.com.estudo.padraocriacao.util.TipoBandeira;

public class ReceptorCreator {

	public Receptor create(final TipoBandeira bandeira) {
		if (TipoBandeira.VISA == bandeira) {
			return new ReceptorVisa();
		} else if (TipoBandeira.MASTER == bandeira) {
			return new ReceptorMastercard();
		} else {
			throw new IllegalArgumentException("Tipo de receptor não suportado.");
		}
	}
}
