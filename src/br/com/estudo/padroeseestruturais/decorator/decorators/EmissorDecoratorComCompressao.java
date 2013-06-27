package br.com.estudo.padroeseestruturais.decorator.decorators;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

import br.com.estudo.padroeseestruturais.decorator.impl.Emissor;

public class EmissorDecoratorComCompressao extends EmissorDecorator {

	public EmissorDecoratorComCompressao(final Emissor emissor) {
		super(emissor);
	}

	@Override
    public void enviar(final String mensagem) {
		System.out.println(" Enviando mensagem comprimida : ");
		String mensagemComprimida;
		try {
			mensagemComprimida = comprime(mensagem);
		} catch (IOException e) {
			mensagemComprimida = mensagem;
		}
		this.getEmissor().enviar(mensagemComprimida);
	}

	private String comprime(final String mensagem) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DeflaterOutputStream dout = new DeflaterOutputStream(out, new Deflater());
		dout.write(mensagem.getBytes());
		dout.close();
		return new String(out.toByteArray());
	}

}
