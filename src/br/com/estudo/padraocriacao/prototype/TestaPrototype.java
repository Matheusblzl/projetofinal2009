package br.com.estudo.padraocriacao.prototype;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import br.com.estudo.padraocriacao.prototype.campanha.Campanha;

/**
 * <p>
 * Objetivo:<br>
 * Possibilitar a criação de novos objetos a partir da cópia de objetos existentes.
 * </p>
 * <p>
 * Exemplo prático:<br>
 * Estamos desenvolvendo um sistema de anúncios semelhante ao do Google Adwords. Nesse sistema,
 * os usuários poderão criar campanhas e configurá-las de acordo com as suas necessidades.
 * Uma campanha é composta por diversas informações, entre elas:<br>
 * • Uma lista de anúncios.<br>
 * • O valor diário máximo que deve ser gasto pela campanha.<br>
 * • O valor máximo por exibição de anúncio.<br>
 * • As datas de início e término.<br>
 * Nesse tipo de sistema, os usuários geralmente criam campanhas com configurações extremamente
 * parecidas. Dessa forma, seria interessante que o sistema tivesse a capacidade de criar uma
 * campanha a partir de uma outra campanha já criada anteriormente, para que as configurações pudessem
 * ser aproveitadas.
 * </p>
 * @author daniel.oliveira
 */
public class TestaPrototype {
	
	public static void main(final String[] args) {
		String nome = " K19 ";
		Calendar vencimento = Calendar.getInstance();
		vencimento.add(Calendar.DATE, 30);
		Set<String> hashSet = new HashSet<String>();
		hashSet.add(" curso ");
		hashSet.add(" java ");
		hashSet.add(" k19 ");

		Campanha campanha = new Campanha(nome, vencimento, hashSet);
		
		System.out.println(campanha);
		Campanha clone = campanha.clone();
		System.out.println(clone);
	}
	
}
