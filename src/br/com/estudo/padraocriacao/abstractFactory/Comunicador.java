package br.com.estudo.padraocriacao.abstractFactory;

import br.com.estudo.padraocriacao.abstractFactory.util.EmissorCreator;
import br.com.estudo.padraocriacao.abstractFactory.util.ReceptorCreator;

public abstract class Comunicador {
	EmissorCreator emissorCreator = new EmissorCreator();
	ReceptorCreator receptorCreator = new ReceptorCreator();
}
