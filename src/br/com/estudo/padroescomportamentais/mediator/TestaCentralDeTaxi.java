package br.com.estudo.padroescomportamentais.mediator;

import br.com.estudo.padroescomportamentais.mediator.model.CentralDeTaxi;
import br.com.estudo.padroescomportamentais.mediator.model.Passageiro;
import br.com.estudo.padroescomportamentais.mediator.model.Taxi;

/**
 *<p>
 * Objetivo:<br>
 * Diminuir a quantidade de “ligações” entre objetos introduzindo um mediador, através
 * do qual toda comunicação entre os objetos será realizada.
 *</p>
 *<p>
 * Exemplo prático:<br>
 * Estamos desenvolvendo um sistema para controlar o fluxo de táxis e passageiros em um aeroporto.
 * Os táxis disponíveis ficam a espera de passageiros em uma fila organizada pela ordem de
 * chegada. Da mesma forma, os passageiros que desejam utilizar um táxi ficam em uma fila de espera
 * que também é organizada pela ordem de chegada.
 * Criaremos uma classe para modelar os passageiros, outra para os táxis e uma terceira para mediar
 * a comunicação entre esses objetos.
 *</p> 
 * @author daniel.oliveira
 */
public class TestaCentralDeTaxi {

	public static void main(final String[] args) {
		CentralDeTaxi central = new CentralDeTaxi();

		Passageiro p1 = new Passageiro(" Rafael Cosentino ", central);
		Passageiro p2 = new Passageiro(" Marcelo Martins ", central);
		Passageiro p3 = new Passageiro(" Jonas Hirata ", central);

		Taxi t1 = new Taxi(central);
		central.adicionaTaxiDisponivel(t1);

		Taxi t2 = new Taxi(central);
		central.adicionaTaxiDisponivel(t2);

		new Thread(p1).start();
		new Thread(p2).start();
		new Thread(p3).start();
	}

}
