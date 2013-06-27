package br.com.estudo.padroeseestruturais.composite;

import br.com.estudo.padroeseestruturais.composite.caminhos.Caminho;
import br.com.estudo.padroeseestruturais.composite.impl.Trecho;
import br.com.estudo.padroeseestruturais.composite.trechos.TrechoAndando;
import br.com.estudo.padroeseestruturais.composite.trechos.TrechoDeCarro;

/**
 * <p>
 * Objetivo:<br>
 * Agrupar objetos que fazem parte de uma relação parte-todo de forma a tratá-los sem distinção.
 * </p>
 * <p>
 * Exemplo prático:<br>
 * Suponha que estamos desenvolvendo um sistema para calcular um caminho entre quaisquer
 * dois pontos do mundo. Um caminho pode ser percorrido de diversas maneiras: à pé, de carro, de
 * ônibus, de trem, de avião, de navio, etc.
 * O sistema deve apresentar graficamente para os usuários as rotas que forem calculadas. Cada
 * tipo de trecho deve ser apresentado de uma maneira específica. Por exemplo, se o trecho for de
 * caminhada então deve aparecer na impressão da rota a ilustração de uma pessoa andando.
 * Cada tipo de trecho pode ser implementado por uma classe e seria interessante definir uma interface
 * para padronizá-las.
 * </p>
 * @author daniel.oliveira
 *
 */
public class TestaCaminho {

	public static void main(final String[] args) {
		Trecho trecho1 = new TrechoAndando("Vá até o cruzamento da Av. Rebouças com a Av. Brigadeiro Faria Lima ", 500);
		Trecho trecho2 = new TrechoDeCarro("Vá até o cruzamento da Av. Brigadeiro Faria Lima com a Av. Cidade Jardim ", 1500);
		Trecho trecho3 = new TrechoDeCarro("Vire a direita na Marginal Pinheiros ", 500);

		Caminho caminho1 = new Caminho();
		caminho1.adiciona(trecho1);
		caminho1.adiciona(trecho2);

		System.out.println("Caminho 1: ");
		caminho1.imprime();

		Caminho caminho2 = new Caminho();
		caminho2.adiciona(caminho1);
		caminho2.adiciona(trecho3);
		
		System.out.println("---------------");
		System.out.println("Caminho2: ");
		caminho2.imprime();
	}

}
