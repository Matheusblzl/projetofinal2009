package br.com.estudo.padraocriacao.singleton;

/**
 * <p>
 * Objetivo:<br>
 * Permitir a criação de uma única instância de uma classe e fornecer um modo para recuperá-la.
 * </p> 
 * <p>
 * Exemplo prático:<br>
 * Suponha que estamos desenvolvendo um sistema que possui configurações globais obtidas a
 * partir de um arquivo de propriedades. Essas configurações podem ser armazenadas emumobjeto.
 * </p>
 * @author daniel.oliveira
 */
public class TestaSingleton {

	public static void main(String[] args) {
		Configuracao configuracao = Configuracao . getInstance ();
		System.out.println ( configuracao . getPropriedade ("time-zone"));
		System.out.println ( configuracao . getPropriedade ("currency-code"));
	}
	
}
