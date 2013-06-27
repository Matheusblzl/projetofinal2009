package br.com.estudo.padroeseestruturais.proxy;

import br.com.estudo.padroeseestruturais.proxy.impl.Conta;
import br.com.estudo.padroeseestruturais.proxy.model.ContaPadrao;
import br.com.estudo.padroeseestruturais.proxy.proxy.ContaProxy;

/**
 * <p>
 * Objetivo:<br>
 * Controlar as chamadas a um objeto através de outro objeto de mesma interface.
 * </p> 
 * <p>
 * Exemplo prático:<br>
 * Estamos desenvolvendo uma aplicação bancária que deve registrar todas as operações realizadas
 * pelos objetos que representam as contas do banco. O registro das operações pode ser utilizado
 * posteriormente em uma auditoria.
 * Para manter o sistema mais coeso, não queremos implementar o registro das operações dentro
 * dos objetos que representam as contas. A idéia é implementar essa lógica em objetos intermediários.
 * Para preservar o modo de utilização das contas, podemos manter a interface nesses objetos
 * intermediários.
 * Podemos definir uma interface para padronizar os métodos dos objetos que representam as contas
 * e os objetos intermediários responsáveis pelo registro das operações.
 * </p>
 * @author daniel.oliveira
 */
public class TesteProxy {

	public static void main(final String[] args) {
		Conta contaProxy = new ContaProxy(new ContaPadrao());
		contaProxy.deposita(100);
		contaProxy.saca(59);
		System.out.println("Saldo: " + contaProxy.getSaldo());
	}

}
