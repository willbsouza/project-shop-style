package br.com.compass.msorder.service.exception;

public class FeignClientException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FeignClientException(String msg) {
		super(msg);
	}
}
