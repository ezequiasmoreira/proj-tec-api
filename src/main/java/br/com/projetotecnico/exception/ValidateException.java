package br.com.projetotecnico.exception;

public class ValidateException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ValidateException(String msg, Throwable throwable) {
		super(msg,throwable);
	}	

}