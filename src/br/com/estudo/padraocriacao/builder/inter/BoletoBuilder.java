package br.com.estudo.padraocriacao.builder.inter;

import java.util.Calendar;

/**
 * Interface que irá padronizar as classes responsáveis pela criação dos boletos
 * @author daniel.oliveira
 */
public interface BoletoBuilder {

	void buildSacado(String sacado);
	void buildCedente(String cedente);
	void buildValor(double valor);
	void buildVencimento(Calendar vencimento);
	void buildNossoNumero(int nossoNumero);
	Boleto getBoleto();
}
