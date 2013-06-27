package br.com.estudo.padroeseestruturais.facade.process;

public class Financeiro {

	public void fatura(final String cliente, final String produto) {
		System.out.println(" Fatura :");
		System.out.println(" Cliente : " + cliente);
		System.out.println(" Produto : " + produto);
	}

}
