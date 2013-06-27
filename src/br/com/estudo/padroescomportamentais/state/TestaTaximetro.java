package br.com.estudo.padroescomportamentais.state;

import br.com.estudo.padroescomportamentais.state.inter.Bandeira;
import br.com.estudo.padroescomportamentais.state.model.Taximetro;

/**
 * Objetivo:
 * Alterar o comportamento de um determinado objeto de acordo com o estado no qual ele se encontra.
 * 
 * Exemplo prático:
 * Estamos trabalhando em uma empresa que vende taxímetros para o mundo todo. Temos que
 * implementar a lógica para calcular o valor das corridas de acordo com a bandeira selecionada no
 * aparelho. O taxímetro pode ser configurado com diversas bandeiras.
 * 
 * @author daniel.oliveira
 */
public class TestaTaximetro {

	public static void main(final String[] args) {
		Bandeira b1 = new Bandeira1();
		Bandeira b2 = new Bandeira2();

		Taximetro taximetro = new Taximetro(b1);

		double valor1 = taximetro.calculaValorDaCorrida(10, 20);
		System.out.println(" Valor da corrida em bandeira 1: " + valor1);

		taximetro.setBandeira(b2);

		double valor2 = taximetro.calculaValorDaCorrida(5, 30);
		System.out.println(" Valor da corrida em bandeira 2: " + valor2);
	}
}
