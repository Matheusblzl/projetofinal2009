package br.com.estudo.padroeseestruturais.bridge.arq;

import br.com.estudo.padroeseestruturais.bridge.impl.Documento;
import br.com.estudo.padroeseestruturais.bridge.impl.GeradorDeArquivo;

public class Recibo implements Documento {

	private String emissor;
	private String favorecido;
	private double valor;
	private GeradorDeArquivo geradorDeArquivo;

	public Recibo(final String emissor, final String favorecido, final double valor, GeradorDeArquivo geradorDeArquivo) {
		this.emissor = emissor;
		this.favorecido = favorecido;
		this.valor = valor;
		this.geradorDeArquivo = geradorDeArquivo;
	}

	@Override
	public void gerarArquivo() {
		StringBuilder builder = new StringBuilder();
		builder.append("-- Recibo --");
		builder.append("\n");
		builder.append("Empresa: ");
		builder.append(this.emissor);
		builder.append("\n");
		builder.append("Cliente: ");
		builder.append(this.favorecido);
		builder.append("\n");
		builder.append("Valor: ");
		builder.append(this.valor);
		
		this.geradorDeArquivo.gera(builder.toString());
	}

}
