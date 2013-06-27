package br.com.estudo.padroescomportamentais.visitor.models;

import br.com.estudo.padroescomportamentais.visitor.interf.AtualizadorDeFuncionario;

public class Gerente extends Funcionario {

	private String senha;

	public Gerente(final String nome, final double salario, final String senha) {
		super(nome, salario);
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	@Override
	public void aceita(AtualizadorDeFuncionario atualizador) {
		atualizador.atualiza(this);
	}

}
