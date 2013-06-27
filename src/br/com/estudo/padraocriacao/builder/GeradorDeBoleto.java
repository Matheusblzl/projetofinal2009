package br.com.estudo.padraocriacao.builder;

import java.util.Calendar;

import br.com.estudo.padraocriacao.builder.inter.Boleto;
import br.com.estudo.padraocriacao.builder.inter.BoletoBuilder;

/**
 * Classe para gerar boletos.
 * @author daniel.oliveira
 */
public class GeradorDeBoleto {

	private BoletoBuilder boletoBuilder;

	public GeradorDeBoleto(BoletoBuilder boletoBuilder) {
		super();
		this.boletoBuilder = boletoBuilder;
	}
	
	public Boleto gerarBoleto() {
		boletoBuilder.buildSacado("Daniel Oliveira");
		boletoBuilder.buildCedente("Copony");
		boletoBuilder.buildValor(158.56d);
		
		Calendar vencimento = Calendar.getInstance();
		vencimento.add(Calendar.DATE, 30);
		boletoBuilder.buildVencimento(vencimento);
		
		boletoBuilder.buildNossoNumero(3211);
		
		return boletoBuilder.getBoleto();
	}
}
