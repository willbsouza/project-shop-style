package br.com.compass.mspayment.controller.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.compass.mspayment.service.exception.ObjectNotFoundException;
import br.com.compass.mspayment.service.exception.PaymentNotValidException;

@RestControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError erro = new StandardError();
		erro.setTimestamp(Instant.now());
		erro.setStatus(HttpStatus.NOT_FOUND.value());
		erro.setError("Not found.");
		erro.setMessage(e.getMessage());
		erro.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> invalidFields(MethodArgumentNotValidException e, HttpServletRequest request){
		StandardError erro = new StandardError();
		erro.setTimestamp(Instant.now());
		erro.setStatus(HttpStatus.BAD_REQUEST.value());
		erro.setError("Field does not comply with policies.");
		erro.setMessage("Incorrect field: " + e.getFieldError().getField().toUpperCase());
		erro.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<StandardError> invalidFields(HttpMessageNotReadableException e, HttpServletRequest request){
		StandardError erro = new StandardError();
		erro.setTimestamp(Instant.now());
		erro.setStatus(HttpStatus.BAD_REQUEST.value());
		erro.setError("Invalid Input. Please select a valid entry.");
		erro.setMessage("Select one of the following valid entries: " + e.getCause().getMessage());
		erro.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> invalidFields(DataIntegrityViolationException e, HttpServletRequest request){		
		StandardError erro = new StandardError();
		erro.setTimestamp(Instant.now());
		erro.setStatus(HttpStatus.BAD_REQUEST.value());
		erro.setError("Field does not comply with policies.");
		erro.setMessage("Check the fields.");
		erro.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(PaymentNotValidException.class)
	public ResponseEntity<StandardError> paymentNotValid(PaymentNotValidException e, HttpServletRequest request){
		
		StandardError erro = new StandardError();
		erro.setTimestamp(Instant.now());
		erro.setStatus(HttpStatus.BAD_REQUEST.value());
		erro.setError("Invalid payment.");
		erro.setMessage(e.getMessage());
		erro.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}

