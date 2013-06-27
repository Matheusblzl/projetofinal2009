package br.com.estudo.padroeseestruturais.adapter.antigo;

import java.util.Calendar;

import br.com.estudo.padroeseestruturais.adapter.model.Funcionario;
import br.com.estudo.util.Util;

public class ControleDePonto {

	public void registraEntrada(final Funcionario f) {
		System.out.println("Entrada: " + f.getNome() + " às " + Util.formatDate(Calendar.getInstance(), Util.DDMMYYYY_HHMMSS));
	}

	public void registraSaida(final Funcionario f) {
		System.out.println(" Saída : " + f.getNome() + " às " + Util.formatDate(Calendar.getInstance(), Util.DDMMYYYY_HHMMSS));
	}

}
