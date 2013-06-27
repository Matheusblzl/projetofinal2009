package br.com.estudo.padroeseestruturais.adapter.novo;

import java.util.Calendar;

import br.com.estudo.padroeseestruturais.adapter.model.Funcionario;
import br.com.estudo.util.Util;

public class ControleDePontoNovo {
	
	public void registra(final Funcionario f, final boolean entrada) {
		String data = Util.formatDate(Calendar.getInstance(), Util.DDMMYYYY_HHMMSS);

		if (entrada == true) {
			System.out.println("Entrada: " + f.getNome() + " às " + data);
		} else {
			System.out.println("Saída: " + f.getNome() + " às " + data);
		}
	}

}
