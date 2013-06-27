package br.com.estudo.padroeseestruturais.proxy.impl;

public interface Conta {
	void deposita ( double valor );
	void saca ( double valor );
	double getSaldo ();
}
