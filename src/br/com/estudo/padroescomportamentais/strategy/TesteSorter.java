package br.com.estudo.padroescomportamentais.strategy;

import java.util.ArrayList;
import java.util.List;

import br.com.estudo.padroescomportamentais.strategy.inter.Sorter;
import br.com.estudo.padroescomportamentais.strategy.sorters.InsertionSorter;
import br.com.estudo.padroescomportamentais.strategy.sorters.SelectionSorter;

/**
 * Objetivo:
 * Permitir de maneira simples a variação dos algoritmos utilizados na resolução de um determinado problema. 
 * 
 * Exemplo prático:
 * Uma tarefa muito comum no desenvolvimento de uma aplicação é ordenar uma lista de elementos.
 * Em Ciência da Computação, foram desenvolvidos diversos algoritmos de ordenação. Podemos
 * escolher o algoritmo mais apropriado de acordo com a situação.
 * De qualquer forma, todos os algoritmos de ordenação devem produzir o mesmo resultado, podendo
 * variar no consumo de memória e no tempo gasto para realizar a ordenação.
 * Podemos definir uma interface para padronizar as diversas implementações dos algoritmos de
 * ordenação.
 * 
 * @author daniel.oliveira
 */
public class TesteSorter {

	public static void main(final String[] args) {
		Sorter sorter = new InsertionSorter();
		List<Integer> list = new ArrayList<Integer>();
		list.add(10);
		list.add(3);
		list.add(2);
		list.add(14);

		List<Integer> list2 = sorter.sort(list);
		
		for (Integer integer : list2) {
			System.out.println(integer);
		}

		Sorter sorter2 = new SelectionSorter();
		
		List<Integer> list3 = sorter2.sort(list);
		
		for (Integer integer : list3) {
			System.out.println(integer);
		}
	}

}
