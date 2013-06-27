package br.com.estudo.padroeseestruturais.decorator;

import br.com.estudo.padroeseestruturais.decorator.decorators.EmissorDecoratorComCompressao;
import br.com.estudo.padroeseestruturais.decorator.decorators.EmissorDecoratorComCriptografia;
import br.com.estudo.padroeseestruturais.decorator.emissor.EmissorBasico;
import br.com.estudo.padroeseestruturais.decorator.impl.Emissor;

/**
 * <p>
 * Objetivo:<br>
 * Adicionar funcionalidade a um objeto dinamicamente.
 * </p>
 * <p>
 * Considere um mapa de ruas digital, como o Google Maps, por exemplo. É natural que o mapa
 * contenha os nomes das ruas. Contudo, o mapa também poderia exibir diversas outras informações
 * (como, por exemplo, relevo, indicadores de estabelecimentos de comércio e serviço), bem como oferecer
 * opções para encontrar caminhos entre dois pontos e traçar rotas.
 * </p>
 * @author daniel.oliveira
 */
public class TesteEmissorDecorator {

	public static void main(final String[] args) {
		String mensagem = "";

		Emissor emissorCript = new EmissorDecoratorComCriptografia(new EmissorBasico());
		emissorCript.enviar(mensagem);

		Emissor emissorCompr = new EmissorDecoratorComCompressao(new EmissorBasico());
		emissorCompr.enviar(mensagem);

		Emissor emissorCriptCompr = new EmissorDecoratorComCriptografia(new EmissorDecoratorComCompressao(new EmissorBasico()));
		emissorCriptCompr.enviar(mensagem);
	}

}
