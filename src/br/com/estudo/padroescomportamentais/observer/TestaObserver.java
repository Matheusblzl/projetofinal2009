package br.com.estudo.padroescomportamentais.observer;

import br.com.estudo.padroescomportamentais.observer.action.Acao;
import br.com.estudo.padroescomportamentais.observer.model.Corretora;

/**
 *<p>
 * Objetivo:<br>
 * Definir um mecanismo eficiente para reagir às alterações realizadas em determinados objetos.
 *</p>
 *<p>
 * Exemplo prático:<br>
 * Estamos desenvolvendo um sistema para o mercado financeiro. Esse sistema deve manter todos
 * os interessados em uma determinada ação informados do valor da mesma. Toda alteração no valor
 * de uma ação deve ser informada aos seus respectivos interessados.
 * Podemos ter vários tipos de interessados. Por exemplo, uma pessoa física, uma empresa, um órgão
 * público, entre outros. Paramanter uma padronização na modelagem, definiremos uma interface
 * para os interessados.
 *</p> 
 * @author daniel.oliveira
 */
public class TestaObserver {

	public static void main(final String[] args) {
		Acao acao = new Acao("VALE3", 45.27);

		Corretora corretora1 = new Corretora("Corretora 01");
		Corretora corretora2 = new Corretora("Corretora 02");

		acao.registraInteressado(corretora1);
		acao.registraInteressado(corretora2);

		acao.setValor(50);
	}

}
