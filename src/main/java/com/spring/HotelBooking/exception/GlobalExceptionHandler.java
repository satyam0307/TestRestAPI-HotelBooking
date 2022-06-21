package com.spring.HotelBooking.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spring.HotelBooking.payload.ErrorDetails;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	//handle specific exception as well as global exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
			WebRequest webrequest){
		ErrorDetails error=new ErrorDetails(new Date(),exception.getMessage(),webrequest.getDescription(false));
		
		return  new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		
		
		
	}
	
	@ExceptionHandler(ReservationApiException.class)
	public ResponseEntity<ErrorDetails> handleReservationApiException(ReservationApiException exception,
			WebRequest webrequest){
		ErrorDetails error=new ErrorDetails(new Date(),exception.getMessage(),webrequest.getDescription(false));
		
		return  new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		
		
		
	}
	
	
	//global exception 
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handlerGlobalExcfeption(Exception  exception,
			WebRequest webrequest){
		ErrorDetails error=new ErrorDetails(new Date(),exception.getMessage(),webrequest.getDescription(false));
		
		return  new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		
		
	}
	
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, 
			HttpStatus status, 
			WebRequest request) {
		// TODO Auto-generated method stub
		
		Map<String,String> errors=new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error) ->{
			String fieldname =((FieldError)error).getField();
			String message=error.getDefaultMessage();
			errors.put(fieldname, message);
		});
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}
	


}
