package br.com.estudo.padraocriacao.builder;

import java.util.Calendar;

import br.com.estudo.padraocriacao.builder.inter.Boleto;
import br.com.estudo.util.Util;

public class BBBoleto implements Boleto {

	private String sacado;
	private String cedente;
	private double valor;
	private Calendar vencimento;
	private int nossoNumero;

	public BBBoleto(final String sacado, final String cedente,
			final double valor, final Calendar vencimento,
			final int nossoNumero) {
		super();
		this.sacado = sacado;
		this.cedente = cedente;
		this.valor = valor;
		this.vencimento = vencimento;
		this.nossoNumero = nossoNumero;
	}

	@Override
	public String getSacado() {
		return this.sacado;
	}

	@Override
	public String getCedente() {
		return this.cedente;
	}

	@Override
	public double getValor() {
		return this.valor;
	}

	@Override
	public Calendar getVencimento() {
		return this.vencimento;
	}

	@Override
	public int getNossoNumero() {
		return this.nossoNumero;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Boleto BB");
		stringBuilder.append("\n");

		stringBuilder.append("Sacado : " + this.sacado);
		stringBuilder.append("\n");

		stringBuilder.append("Cedente : " + this.cedente);
		stringBuilder.append("\n");

		stringBuilder.append("Valor : " + this.valor);
		stringBuilder.append("\n");

		stringBuilder.append("Vencimento : " + this.sacado);
		stringBuilder.append("\n");

		stringBuilder.append("Vencimento : " + Util.formatDate(vencimento, Util.DDMMYYYY));
		stringBuilder.append("\n");

		stringBuilder.append("Nosso Número : " + this.nossoNumero);
		stringBuilder.append("\n");

		return stringBuilder.toString();
	}

}
