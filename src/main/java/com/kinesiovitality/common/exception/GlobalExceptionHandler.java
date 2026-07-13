package com.kinesiovitality.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kinesiovitality.common.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<Object>> handleResourceNotFound(ResourceNotFoundException ex) {

	    ApiResponse<Object> response = new ApiResponse<>();

	    response.setSuccess(false);
	    response.setMessage(ex.getMessage());
	    response.setData(null);

	    return ResponseEntity
	            .status(HttpStatus.NOT_FOUND)
	            .body(response);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Object>> handleException(Exception ex) {

	    ApiResponse<Object> response = new ApiResponse<>();

	    response.setSuccess(false);
	    response.setMessage("Ha ocurrido un error interno en el servidor.");
	    response.setData(null);

	    return ResponseEntity
	            .status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body(response);

	}

}