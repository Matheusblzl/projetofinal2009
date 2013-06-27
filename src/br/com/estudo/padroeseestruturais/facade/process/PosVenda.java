package br.com.estudo.padroeseestruturais.facade.process;

import java.util.Calendar;

import br.com.estudo.util.Util;

public class PosVenda {

	public void agendaContato(final String cliente, final String produto) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 30);
		String format = Util.formatDate(calendar, Util.DDMMYYYY);

		System.out.println(" Entrar em contato com " + cliente + " sobre o produto " + produto + " no dia " + format);
	}

}
