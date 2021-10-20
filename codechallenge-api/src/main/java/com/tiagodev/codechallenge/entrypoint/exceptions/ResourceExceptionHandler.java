package com.tiagodev.codechallenge.entrypoint.exceptions;

import com.tiagodev.codechallenge.core.exceptions.UseCaseException;
import com.tiagodev.codechallenge.dataprovider.exceptions.DataProviderException;
import com.tiagodev.codechallenge.dataprovider.exceptions.NotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(UseCaseException.class)
	public ResponseEntity<Object> useCaseException(UseCaseException e, HttpServletRequest request){

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", System.currentTimeMillis());
		body.put("message", e.getMessage());

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> notFoundException(NotFoundException e, HttpServletRequest request){

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", System.currentTimeMillis());
		body.put("message", e.getMessage());

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataProviderException.class)
	public ResponseEntity<Object> dataProviderException(DataProviderException e, HttpServletRequest request){

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", System.currentTimeMillis());
		body.put("message", e.getMessage());

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

}
