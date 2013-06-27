package br.com.estudo.padroescomportamentais.visitor.models;

import java.util.ArrayList;
import java.util.List;

import br.com.estudo.padroescomportamentais.visitor.interf.AtualizadorDeFuncionario;

public class Departamento {

	private String nome;
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();

	public Departamento(final String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void aceita(final AtualizadorDeFuncionario atualizador) {
		for (Funcionario f : this.funcionarios) {
			f.aceita(atualizador);
		}
	}

}
