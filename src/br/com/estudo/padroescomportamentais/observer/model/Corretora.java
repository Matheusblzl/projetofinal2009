package br.com.estudo.padroescomportamentais.observer.model;

import br.com.estudo.padroescomportamentais.observer.action.Acao;
import br.com.estudo.padroescomportamentais.observer.inter.AcaoObserver;

public class Corretora implements AcaoObserver {

	private String nome;

	public Corretora(final String nome) {
		this.nome = nome;
	}

	@Override
	public void notificaAlteracao(final Acao acao) {
		System.out.println(" Corretora " + this.nome + " sendo notificada :");
		System.out.println("A ação " + acao.getCodigo() + " teve o seu valor alterado para " + acao.getValor());
	}

}
