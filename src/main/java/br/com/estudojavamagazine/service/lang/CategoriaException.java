package br.com.estudojavamagazine.service.lang;

public class CategoriaException extends Exception {

	private static final long serialVersionUID = 1L;

	public CategoriaException() {
		super();
	}

	public CategoriaException(String message, Throwable cause) {
		super(message, cause);
	}

	public CategoriaException(String message) {
		super(message);
	}

	public CategoriaException(Throwable cause) {
		super(cause);
	}

}
