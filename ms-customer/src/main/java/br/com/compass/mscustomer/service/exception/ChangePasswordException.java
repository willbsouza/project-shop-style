package br.com.compass.mscustomer.service.exception;

public class ChangePasswordException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ChangePasswordException(String msg) {
		super(msg);
	}

}
