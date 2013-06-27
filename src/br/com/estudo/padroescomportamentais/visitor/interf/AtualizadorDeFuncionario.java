package br.com.estudo.padroescomportamentais.visitor.interf;

import br.com.estudo.padroescomportamentais.visitor.models.Gerente;
import br.com.estudo.padroescomportamentais.visitor.models.Telefonista;

public interface AtualizadorDeFuncionario {

	void atualiza ( Gerente g);
	void atualiza ( Telefonista t);
	
}
