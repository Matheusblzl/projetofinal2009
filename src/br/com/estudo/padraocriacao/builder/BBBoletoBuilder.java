package br.com.estudo.padraocriacao.builder;

import java.util.Calendar;

import br.com.estudo.padraocriacao.builder.inter.Boleto;
import br.com.estudo.padraocriacao.builder.inter.BoletoBuilder;

/**
 * Classe responsável pela criação de boletos do banco BB.
 * @author daniel.oliveira
 */
public class BBBoletoBuilder implements BoletoBuilder {

	private String sacado;
	private String cedente;
	private double valor;
	private Calendar vencimento;
	private int nossoNumero;

	@Override
	public void buildSacado(final String sacado) {
		this.sacado = sacado;
	}

	@Override
	public void buildCedente(final String cedente) {
		this.cedente = cedente;
	}

	@Override
	public void buildValor(final double valor) {
		this.valor = valor;
	}

	@Override
	public void buildVencimento(final Calendar vencimento) {
		this.vencimento = vencimento;
	}

	@Override
	public void buildNossoNumero(final int nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

	@Override
	public Boleto getBoleto() {
		return new BBBoleto(sacado, cedente, valor, vencimento, nossoNumero);
	}

}
