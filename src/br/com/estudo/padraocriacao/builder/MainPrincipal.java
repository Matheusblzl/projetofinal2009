package br.com.estudo.padraocriacao.builder;

import br.com.estudo.padraocriacao.builder.inter.Boleto;
import br.com.estudo.padraocriacao.builder.inter.BoletoBuilder;

/**
 * <p>
 * Objetivo:<br>
 * Separar o processo de construção de um objeto de sua representação e permitir a sua
 * criação passo-a-passo. Diferentes tipos de objetos podem ser criados com implementações distintas de
 * cada passo.
 * </p>
 * <p>
 * Exemplo prático:<br>
 * Estamos desenvolvendo um sistema para gerar boletos bancários. No Brasil, a FEBRABAN (Federação
 * Brasileira de Bancos) define regras gerais para os boletos. Contudo, cada instituição bancária
 * define também as suas próprias regras específicas para o formato dos dados dos boletos.
 * Segundo a FEBRABAN, os elementos principais relacionados a umboleto são:
 * </P>
 * <ul>
 * <li>
 * Sacado:<br>
 * Pessoa ou empresa responsável pelo pagamento do boleto.
 * </li>
 * <li>
 * Cedente:<br>
 * Pessoa ou empresa que receberá o pagamento do boleto.
 * </li>
 * <li>
 * Banco:<br>
 * Instituição que receberá o pagamento do sacado e creditará o valor na conta do cedente.
 * </li>
 * <li>
 * Código de Barras:<br>
 * Representação gráfica das informações do boleto.
 * </li>
 * <li>
 * Linha Digitável:<br>
 * Representação numérica das informações do boleto.
 * </li>
 * <li>
 * Nosso Número:<br>
 * Código de identificação do boleto utilizado pela instituição bancária e pelo cedente para controle dos pagamentos.
 * </li>
 * </ul>
 * @author daniel.oliveira
 *
 */
public class MainPrincipal {

	public static void main(String[] args) {
		BoletoBuilder boletoBuilder = new BBBoletoBuilder();
		GeradorDeBoleto geradorDeBoleto = new GeradorDeBoleto(boletoBuilder);
		
		Boleto boleto = geradorDeBoleto.gerarBoleto();
		
		System.out.println(boleto);
	}
}
