package br.com.estudo.padraocriacao.prototype.campanha;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import br.com.estudo.padraocriacao.prototype.interf.Prototype;
import br.com.estudo.util.Util;

public class Campanha implements Prototype<Campanha> {

	private String nome;
	private Calendar vencimento;
	private Set<String> palavrasChave;

	public Campanha() {
		super();
	}

	public Campanha(final String nome, final Calendar vencimento, final Set<String> palavrasChave) {
		super();
		this.nome = nome;
		this.vencimento = vencimento;
		this.palavrasChave = palavrasChave;
	}

	@Override
	public Campanha clone() {
		String nome = "Cópia da Campanha: " + this.nome;
		Calendar vencimento = (Calendar) this.vencimento.clone();
		Set<String> palavrasChave = new HashSet<String>(this.palavrasChave);
		Campanha campanha = new Campanha(nome, vencimento, palavrasChave);
		return campanha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public Calendar getVencimento() {
		return vencimento;
	}

	public void setVencimento(final Calendar vencimento) {
		this.vencimento = vencimento;
	}

	public Set<String> getPalavrasChave() {
		return palavrasChave;
	}

	public void setPalavrasChave(final Set<String> palavrasChave) {
		this.palavrasChave = palavrasChave;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" ---------------");
		builder.append("\n");
		builder.append(" Nome da Campanha : ");
		builder.append(this.nome);
		builder.append("\n");
		builder.append(" Vencimento : " + Util.formatDate(this.vencimento, Util.DDMMYYYY));
		builder.append("\n");

		builder.append(" Palavras - chave : \n");
		for (String palavraChave : this.palavrasChave) {
			builder.append(" - " + palavraChave);
			builder.append("\n");
		}
		builder.append(" ---------------");
		builder.append("\n");
		
		return builder.toString();
	}

}
