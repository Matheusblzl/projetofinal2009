package br.com.estudo.padroeseestruturais.decorator.decorators;

import br.com.estudo.padroeseestruturais.decorator.impl.Emissor;

public class EmissorDecoratorComCriptografia extends EmissorDecorator {

	public EmissorDecoratorComCriptografia(final Emissor emissor) {
		super(emissor);
	}

	private String criptografa(final String mensagem) {
		String mensagemCriptografada = new StringBuilder(mensagem).reverse().toString();
		return mensagemCriptografada;
	}

	@Override
	public void enviar(String mensagem) {
		System.out.println(" Enviando mensagem criptografada : ");
		this.getEmissor().enviar(criptografa(mensagem));
	}

}
