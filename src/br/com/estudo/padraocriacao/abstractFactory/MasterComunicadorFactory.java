package br.com.estudo.padraocriacao.abstractFactory;

import br.com.estudo.padraocriacao.abstractFactory.inter.ComunicadorFactory;
import br.com.estudo.padraocriacao.abstractFactory.inter.Emissor;
import br.com.estudo.padraocriacao.abstractFactory.inter.Receptor;
import br.com.estudo.padraocriacao.util.TipoBandeira;

public class MasterComunicadorFactory extends Comunicador implements ComunicadorFactory {

	@Override
	public Emissor createEmissor() {
		return emissorCreator.create(TipoBandeira.MASTER);
	}

	@Override
	public Receptor createrReceptor() {
		return receptorCreator.create(TipoBandeira.MASTER);
	}

}
