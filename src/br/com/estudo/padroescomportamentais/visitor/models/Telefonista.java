package br.com.estudo.padroescomportamentais.visitor.models;

import br.com.estudo.padroescomportamentais.visitor.interf.AtualizadorDeFuncionario;

public class Telefonista extends Funcionario {

	private int ramal;

	public Telefonista(final String nome, final double salario, final int ramal) {
		super(nome, salario);
		this.ramal = ramal;
	}

	public int getRamal() {
		return ramal;
	}

	@Override
	public void aceita(AtualizadorDeFuncionario atualizador) {
		atualizador.atualiza(this);
	}

}
