package br.com.estudo.padraocriacao.prototype.interf;

/**
 * Possibilitar a criação de novos objetos a partir da cópia de objetos existentes.
 * @author daniel.oliveira
 *
 * @param <T> O tipo a ser clonado.
 */
public interface Prototype<T> {

	T clone();
	
}
