package br.com.compass.msaudit.controller.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.compass.msaudit.service.exception.ObjectNotFoundException;

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
}
