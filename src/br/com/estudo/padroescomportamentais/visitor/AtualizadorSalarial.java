package br.com.estudo.padroescomportamentais.visitor;

import br.com.estudo.padroescomportamentais.visitor.interf.AtualizadorDeFuncionario;
import br.com.estudo.padroescomportamentais.visitor.models.Gerente;
import br.com.estudo.padroescomportamentais.visitor.models.Telefonista;

public class AtualizadorSalarial implements AtualizadorDeFuncionario {

	@Override
	public void atualiza(final Gerente g) {
		g.setSalario(g.getSalario() * 1.43);
	}

	@Override
	public void atualiza(final Telefonista t) {
		t.setSalario(t.getSalario() * 1.27);
	}

}
