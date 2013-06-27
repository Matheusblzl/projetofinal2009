package br.com.estudo.padroeseestruturais.bridge;

import br.com.estudo.padroeseestruturais.bridge.arq.Recibo;
import br.com.estudo.padroeseestruturais.bridge.geradores.GeradorDeArquivoTxt;
import br.com.estudo.padroeseestruturais.bridge.impl.GeradorDeArquivo;

/**
 * <p>
 * Objetivo:<br>
 * Separar uma abstração de sua representação, de forma que ambos possam variar e produzir tipos de objetos diferentes.
 * </p>
 * <p>
 * Exemplo prático:<br>
 * Estamos desenvolvendo um sistema que deve gerar diversos tipos de documentos (recibos, atestados,
 * comunicados, etc) em diversos formatos de arquivos (txt, html, pdf, etc).
 * Podemos definir uma interface para padronizar os diversos tipos de documentos que o nosso
 * sistema pode suportar.
 * </p>
 * @author daniel.oliveira
 */
public class TesteRecibo {

	public static void main(String[] args) {
		GeradorDeArquivo geradorDeArquivo = new GeradorDeArquivoTxt();
		Recibo recibo = new Recibo("Treinamento", "Daniel Oliveira", 1000, geradorDeArquivo);
		recibo.gerarArquivo();
	}
	
}
