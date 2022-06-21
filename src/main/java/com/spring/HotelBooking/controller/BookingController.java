package com.spring.HotelBooking.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.HotelBooking.entity.Booking;
import com.spring.HotelBooking.exception.ReservationApiException;
import com.spring.HotelBooking.exception.ResourceNotFoundException;
import com.spring.HotelBooking.payload.BookingDto;
import com.spring.HotelBooking.repository.BookingRepository;
import com.spring.HotelBooking.service.BookingService;




@RestController
@RequestMapping("/api/")


public class BookingController {
	LocalDate date=LocalDate.now();
	
	private BookingService bookingservice;
	

	public BookingController(BookingService bookingservice) {
		super();
		this.bookingservice = bookingservice;
	}
	
	
	
	@PostMapping("/users/{userid}/booking")
	public ResponseEntity<BookingDto> createBooking(@Valid @PathVariable(value="userid")Long userid ,
			@RequestBody BookingDto bookingdto){
			ResponseEntity<BookingDto> entity = null;
				
		if(!(bookingdto.getCheckInDate()).isBefore(date)) {
			
			if(!(bookingdto.getCheckOutDate().isBefore(bookingdto.getCheckInDate()))) {
				 
				long days=  ChronoUnit.DAYS.between(bookingdto.getCheckInDate(), bookingdto.getCheckOutDate());
				 
				 if(days>=1 && days<3)
				 {
			  if(bookingservice.existsCheckIn(bookingdto)) {
				  
					entity =new ResponseEntity<>(bookingservice.createbooking(userid, bookingdto),HttpStatus.CREATED);
					
					 } else {throw new ReservationApiException(HttpStatus.
					 BAD_REQUEST,"No avaibility there , check for furher dates");}
					 
				 }
					else { 
				 throw new ReservationApiException(HttpStatus.BAD_REQUEST,"Maximum number of days stayed allowed is 3");
			}
			}else {throw new ReservationApiException(HttpStatus.BAD_REQUEST,"CheckOutDate must be future date from ChekInDate ");}
	}else {
		throw new ReservationApiException(HttpStatus.BAD_REQUEST,"CheckInDate must be currentdate or future date ");}
		
	

		 return entity;
		
		
	}
	
	//Cancelled reservation APi through reservation Id
	@PutMapping("/users/{userid}/booking/{res_id}")
	public ResponseEntity<BookingDto> updateStatus(@PathVariable(value="userid") Long userid ,
			@PathVariable(value="res_id") Long res_id,@RequestBody BookingDto bookingdto)
	{
		
		BookingDto updatedbooking =bookingservice.updateStatus(userid, res_id, bookingdto);
		return new ResponseEntity<>(updatedbooking,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/users/{userid}/booking/{res_id}")
	public ResponseEntity<BookingDto> getReservationById(@PathVariable(value="userid")Long userid,
			@PathVariable(value="res_id") Long res_id){
		
		BookingDto bookingdto=bookingservice.getbookingByResId(userid, res_id);
		return new ResponseEntity<>(bookingdto,HttpStatus.OK);
		
	}
	
	
	
	
	
}
