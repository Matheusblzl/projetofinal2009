package br.com.estudo.padroeseestruturais.adapter.adaptador;

import br.com.estudo.padroeseestruturais.adapter.antigo.ControleDePonto;
import br.com.estudo.padroeseestruturais.adapter.model.Funcionario;
import br.com.estudo.padroeseestruturais.adapter.novo.ControleDePontoNovo;

public class ControleDePontoAdapter extends ControleDePonto {

	private ControleDePontoNovo controleDePontoNovo;

	public ControleDePontoAdapter() {
		this.controleDePontoNovo = new ControleDePontoNovo();
	}

	@Override
    public void registraEntrada(final Funcionario f) {
		this.controleDePontoNovo.registra(f, true);
	}

	@Override
    public void registraSaida(final Funcionario f) {
		this.controleDePontoNovo.registra(f, false);
	}

}
