package com.spring.HotelBooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



public class ReservationApiException  extends RuntimeException {
		private HttpStatus httpstatus;
		private String message ;
		
		
		public ReservationApiException(HttpStatus httpstatus, String message) {
			super();
			this.httpstatus = httpstatus;
			this.message = message;
		}
		
		
		public HttpStatus getHttpstatus() {
			return httpstatus;
		}
		public void setHttpstatus(HttpStatus httpstatus) {
			this.httpstatus = httpstatus;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
		
	
	
}
