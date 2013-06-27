package br.com.estudo.padroescomportamentais.templateMethod;

import br.com.estudo.padroescomportamentais.templateMethod.models.Conta;
import br.com.estudo.padroescomportamentais.templateMethod.models.ContaCorrente;
import br.com.estudo.padroescomportamentais.templateMethod.models.ContaPoupanca;

/**
 * Objetivo:
 * Definir a ordem na qual determinados passos devem ser realizados na resolução de um
 * problema e permitir que esses passos possam ser realizados de formas diferentes de acordo com a situação.
 * 
 * Exemplo prático:
 * Estamos desenvolvendo um sistema bancário e precisamos modelar os diversos tipos de contas
 * do banco. Decidimos aplicar herança utilizando uma classe chamada Conta.
 * Toda operação bancária realizada gera a cobrança de uma taxa que é diferente para cada tipo da
 * conta. Podemos tentar implementar um método para calcular essa taxa na classe Conta e chamá-lo
 * a partir dos outros métodos.
 * Contudo, nada pode ser definido no corpo do método calculaTaxa() na classe Conta porque o
 * cálculo é diferente para cada tipo de conta bancária. A solução é tornar o método abstrato para que
 * ele seja implementado nas classes das contas específicas. Dessa forma, os métodos que chamam o
 * calculaTaxa() dependem de um método que será implementado posteriormente.
 * 
 * @author daniel.oliveira
 */
public class TestaContas {

	public static void main(final String[] args) {
		Conta contaCorrente = new ContaCorrente();
		Conta contaPoupanca = new ContaPoupanca();

		contaCorrente.deposita(100);
		contaCorrente.saca(10);

		contaPoupanca.deposita(100);
		contaPoupanca.saca(10);

		System.out.println("Saldo da Conta Corrente : " + contaCorrente.getSaldo());
		System.out.println("Saldo da Conta Poupança : " + contaPoupanca.getSaldo());
	}

}
