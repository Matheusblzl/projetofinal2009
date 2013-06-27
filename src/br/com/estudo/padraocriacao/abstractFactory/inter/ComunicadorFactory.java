package br.com.estudo.padraocriacao.abstractFactory.inter;

public interface ComunicadorFactory {
	Emissor createEmissor();
	Receptor createrReceptor();
}
