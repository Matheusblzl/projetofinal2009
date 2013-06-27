package br.com.estudo.padraocriacao.abstractFactory;

import br.com.estudo.padraocriacao.abstractFactory.inter.ComunicadorFactory;
import br.com.estudo.padraocriacao.abstractFactory.inter.Emissor;
import br.com.estudo.padraocriacao.abstractFactory.inter.Receptor;
import br.com.estudo.padraocriacao.util.TipoBandeira;

public class VisaComunicadorFactory extends Comunicador implements ComunicadorFactory {

	@Override
	public Emissor createEmissor() {
		return emissorCreator.create(TipoBandeira.VISA);
	}

	@Override
	public Receptor createrReceptor() {
		return receptorCreator.create(TipoBandeira.VISA);
	}

}
