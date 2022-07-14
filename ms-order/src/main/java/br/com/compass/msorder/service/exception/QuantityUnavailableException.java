package br.com.compass.msorder.service.exception;

public class QuantityUnavailableException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public QuantityUnavailableException(String msg) {
		super(msg);
	}
}
