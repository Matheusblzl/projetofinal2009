package br.com.estudo.padroescomportamentais.templateMethod.models;

/**
 * Classe abstrata que define os templates methods baseados em métodos abstratos que serão implementados nas ConcreteClasses.
 * @author daniel.oliveira
 */
public abstract class Conta {

	private double saldo;

	public void deposita(final double valor) {
		this.saldo += valor;
		this.saldo -= this.calculaTaxa();
	}

	public void saca(final double valor) {
		this.saldo -= valor;
		this.saldo -= this.calculaTaxa();
	}

	public double getSaldo() {
		return saldo;
	}

	public abstract double calculaTaxa();
}
