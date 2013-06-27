package br.com.estudo.padraocriacao.factoryMethod;

/**
 * <p>
 * Objetivo:<br>
 * Encapsular a escolha da classe concreta a ser utilizada na criação de objetos de um determinado tipo.
 * </p>
 * <p>
 * Exemplo prático:<br>
 * Considere um sistema bancário que precisa enviar mensagens aos seus clientes. Por exemplo,
 * após a realização de uma compra com cartão de crédito, uma mensagem contendo informações
 * sobre a compra pode ser enviada ao cliente.
 * Se esse cliente for uma pessoa física, poderá optar pelo recebimento da mensagem através de
 * email ou SMS. Por outro lado, se for uma pessoa jurídica, poderá também receber a mensagem através
 * de JMS (Java Message Service).
 * </p> 
 * @author daniel.oliveira
 */
public class TesteEmissores {

	public static void main(String[] args) {
		EmissorCreator creator = new EmissorCreator();
		
		Emissor emissor1 = creator.create(EmissorType.SMS);
		emissor1.enviar("oi");
		
		Emissor emissor2 = creator.create(EmissorType.EMAIL);
		emissor2.enviar("oi");
		
		Emissor emissor3 = creator.create(EmissorType.JMS);
		emissor3.enviar("oi");
	}
	
}
