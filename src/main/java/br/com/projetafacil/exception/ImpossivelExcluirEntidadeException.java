package br.com.projetafacil.exception;

public class ImpossivelExcluirEntidadeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ImpossivelExcluirEntidadeException(String msg) {
		super(msg);
	}

}
