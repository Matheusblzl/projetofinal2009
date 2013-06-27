package br.com.estudo.padroeseestruturais.facade.process;

import java.util.Calendar;

import br.com.estudo.util.Util;

public class Estoque {

	public void enviaProduto(final String produto, final String enderecoDeEntrega) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 2);
		String format = Util.formatDate(calendar, Util.DDMMYYYY);

		System.out.println("O produto " + produto + " será entregue no endereço " + enderecoDeEntrega + " até as 18h do dia " + format);
	}

}
