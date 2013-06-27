package br.com.estudo.padraocriacao.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe que permite a criação de uma única instância e fornece um método estático para recuperá-la.
 * @author daniel.oliveira
 */
public class Configuracao {

	private Map<String, String> propriedades;
	private static Configuracao instance;
	
	private Configuracao() {
		propriedades = new HashMap<String, String>();
		propriedades.put("time-zone", "America / Sao_Paulo");
		propriedades.put("currency-code", "BRL");
	}
	
	public static Configuracao getInstance() {
		if (Configuracao.instance == null) {
			Configuracao.instance = new Configuracao();
		}
		
		return Configuracao.instance;
	}
	
	public String getPropriedade(final String nomePropriedade) {
		return this.propriedades.get(nomePropriedade);
	}
}
