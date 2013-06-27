package br.com.estudo.padroeseestruturais.adapter;

import br.com.estudo.padroeseestruturais.adapter.adaptador.ControleDePontoAdapter;
import br.com.estudo.padroeseestruturais.adapter.antigo.ControleDePonto;
import br.com.estudo.padroeseestruturais.adapter.model.Funcionario;

/**
 * <p>
 * Objetivo:<br>
 * Permitir que um objeto seja substituído por outro que, apesar de realizar a mesma tarefa,
 * possui uma interface diferente.
 * </p>
 * <p>
 * Exemplo prático:</br>
 * Estamos realizando manutenção no sistema de gerenciamento de uma determinada empresa. O
 * controle de ponto desse sistema possui diversas limitações. Essas limitações causam muitos prejuízos.
 * Principalmente, prejuízos financeiros.
 * Uma empresa parceira implementou uma biblioteca Java para controlar a entrada e saída dos
 * funcionários. Essa biblioteca não possui as limitações que existem hoje no sistema que estamos
 * realizando manutenção. Os diretores decidiram que a melhor estratégia seria adquirir essa biblioteca
 * e implantá-la no sistema.
 * Para implantar essa biblioteca, teremos que substituir as classes que atualmente cuidam do controle
 * de ponto pelas classes dessa biblioteca. A complexidade dessa substituição é alta pois os métodos
 * das classes antigas não são compatíveis com os métodos das classes novas. Em outras palavras, as interfaces são diferentes.
 * Para tentar minimizar o impacto dessa substituição no código do sistema, podemos definir classes
 * intermediárias para adaptar as chamadas às classes da biblioteca que foi adquirida.
 * </p>
 * @author daniel.oliveira
 */
public class TesteControleDePonto {

	public static void main ( String [] args ) throws InterruptedException {
	 ControleDePonto controleDePonto = new ControleDePontoAdapter();
	 Funcionario funcionario = new Funcionario ("Marcelo Martins");
	 controleDePonto.registraEntrada (funcionario);
	 Thread.sleep (3000) ;
	 controleDePonto.registraSaida (funcionario);
	 }
	
}
