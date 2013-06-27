package br.com.estudo.padroeseestruturais.bridge.geradores;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import br.com.estudo.padroeseestruturais.bridge.impl.GeradorDeArquivo;

public class GeradorDeArquivoTxt implements GeradorDeArquivo {

	@Override
	public void gera(String conteudo) {
		System.out.println("Gerando arquivo...");
		try {
			PrintStream saida = new PrintStream("recibo.txt");
			saida.println(conteudo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Arquivo gerado com sucesso!");
	}

}
