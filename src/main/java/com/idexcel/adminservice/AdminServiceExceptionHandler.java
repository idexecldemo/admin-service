package com.idexcel.adminservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.idexcel.adminservice.dto.ErrorResponseDTO;
import com.idexcel.adminservice.exceptions.LenderAlreadyExistsException;
import com.idexcel.adminservice.exceptions.LenderNotFoundException;

@ControllerAdvice
public class AdminServiceExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = {LenderAlreadyExistsException.class})
	protected ResponseEntity<Object> handleConflict(Exception e) {
		return new ResponseEntity<>(new ErrorResponseDTO(e.getMessage()), HttpStatus.CONFLICT);
	}
	@ExceptionHandler(value = {LenderNotFoundException.class})
	protected ResponseEntity<Object> handleNotFound(Exception e) {
		return new ResponseEntity<>(new ErrorResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
	}
}
