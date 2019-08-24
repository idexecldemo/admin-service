package com.idexcel.adminservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.idexcel.adminservice.dto.ErrorResponse;
import com.idexcel.adminservice.exceptions.LenderAlreadyExistsException;

@ControllerAdvice
public class AdminServiceExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = {LenderAlreadyExistsException.class})
	protected ResponseEntity<Object> handleConflict(Exception e) {
		return new ResponseEntity<Object>(new ErrorResponse(e.getMessage()), HttpStatus.CONFLICT);
		
	}

}
