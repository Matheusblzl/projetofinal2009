package br.com.estudo.padraocriacao.factoryMethod;

import br.com.estudo.padraocriacao.factoryMethod.impl.EmissorEmail;
import br.com.estudo.padraocriacao.factoryMethod.impl.EmissorJMS;
import br.com.estudo.padraocriacao.factoryMethod.impl.EmissorSMS;

public class EmissorCreator {

	public Emissor create(final EmissorType emissorType) {
		if (EmissorType.SMS.equals(emissorType)) {
			return new EmissorSMS();
		}
		if (EmissorType.EMAIL.equals(emissorType)) {
			return new EmissorEmail();
		}
		if (EmissorType.JMS.equals(emissorType)) {
			return new EmissorJMS();
		} else {
			throw new IllegalArgumentException("Tipo não suportado.");
		}
	}
}
