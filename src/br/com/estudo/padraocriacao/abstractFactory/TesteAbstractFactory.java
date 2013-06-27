package br.com.estudo.padraocriacao.abstractFactory;

import br.com.estudo.padraocriacao.abstractFactory.inter.ComunicadorFactory;
import br.com.estudo.padraocriacao.abstractFactory.inter.Emissor;
import br.com.estudo.padraocriacao.abstractFactory.inter.Receptor;

/**
 * <p>
 * Objetivo:<br>
 * Encapsular a escolha das classes concretas a serem utilizadas na criação dos objetos de diversas famílias.
 * </p>
 * <p>
 * Exemplo prático:<br>
 * Estabelecimentos comerciais normalmente oferecem aos clientes diversas opções de pagamento.
 * Por exemplo, clientes podem efetuar pagamentos com dinheiro, cheque, cartões de crédito ou débito,
 * entre outros.
 * Pagamentos com cartões são realizados por meio de uma máquina de cartão, oferecida e instalada
 * por empresas como Cielo e Redecard. Geralmente, essa máquina é capaz de lidar com cartões
 * de diferentes bandeiras (como Visa eMastercard).
 * Nosso objetivo é programar essas máquinas, isto é, desenvolver uma aplicação capaz de se comunicar
 * comas diferentes bandeiras e registrar pagamentos.
 * No momento do pagamento, a máquina de cartão deve enviar as informações relativas a transação
 * (como valor e senha) para a bandeira correspondente ao cartão utilizado. Além disso, a máquina
 * deve aguardar uma resposta de confirmação ou recusa do pagamento.
 * </p>
 * @author daniel.oliveira
 */
public class TesteAbstractFactory {

	private static final ComunicadorFactory comunicadorFactoryVisa = new VisaComunicadorFactory();
	private static final ComunicadorFactory comunicadorFactoryMaster = new MasterComunicadorFactory();
	
	public static void main(String[] args) {
		//Teste VISA.
		String transacaoVisa = "senha:2563;valor:125.63";
		Emissor emissorVisa = comunicadorFactoryVisa.createEmissor();
		emissorVisa.enviar(transacaoVisa);
		
		Receptor receptorVisa = comunicadorFactoryVisa.createrReceptor();
		System.out.println(receptorVisa.receber());
		
		// Teste MASTER
		String transacaoMaster = "senha:4526;valor:42.96";
		Emissor emissorMaster = comunicadorFactoryMaster.createEmissor();
		emissorMaster.enviar(transacaoMaster);
		
		Receptor receptorMaster = comunicadorFactoryMaster.createrReceptor();
		System.out.println(receptorMaster.receber());
	}
}
