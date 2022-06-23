package br.com.compass.mspayment.service.exception;

public class PaymentNotValidException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public PaymentNotValidException(String msg) {
		super(msg);
	}

}
