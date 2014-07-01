package br.com.estudojavamagazine.service.lang;

public class ProdutoException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProdutoException() {
		super();
	}

	public ProdutoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProdutoException(String message) {
		super(message);
	}

	public ProdutoException(Throwable cause) {
		super(cause);
	}

}
