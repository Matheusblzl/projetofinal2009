package br.com.estudo.padraocriacao.factoryMethod;

/**
 * Encapsular a escolha da classe concreta a ser utilizada na criação de objetos de um determinado tipo.
 */
public interface Emissor {
	void enviar(String mensagem);
}
