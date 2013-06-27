package br.com.estudo.padroescomportamentais.templateMethod.models;

/**
 * Classes concretas que implementam os métodos abstratos definidos pela AbstractClass e que são utilizados pelos templates methods.
 * 
 * @see Conta
 * @author daniel.oliveira
 */
public class ContaCorrente extends Conta {

	@Override
	public double calculaTaxa() {
		return 3;
	}

}
