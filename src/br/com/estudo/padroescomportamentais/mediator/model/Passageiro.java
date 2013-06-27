package br.com.estudo.padroescomportamentais.mediator.model;

public class Passageiro implements Runnable {

	private String nome;
	private CentralDeTaxi central;

	public Passageiro(final String nome, final CentralDeTaxi central) {
		this.nome = nome;
		this.central = central;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			this.central.pedeTaxi(this);
		}
	}

}
