package br.com.estudo.padroescomportamentais.visitor.models;

import br.com.estudo.padroescomportamentais.visitor.interf.Atualizavel;

public abstract class Funcionario implements Atualizavel {

	private String nome;
	private double salario;

	public Funcionario(final String nome, final double salario) {
		this.nome = nome;
		this.salario = salario;
	}

	public String getNome() {
		return nome;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(final double salario) {
		this.salario = salario;
	}

}
