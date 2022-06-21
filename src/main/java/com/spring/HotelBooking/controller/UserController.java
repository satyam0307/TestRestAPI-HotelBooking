package com.spring.HotelBooking.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.HotelBooking.exception.ReservationApiException;
import com.spring.HotelBooking.payload.UserDto;
import com.spring.HotelBooking.service.UserService;




@RestController
@RequestMapping("/api/users")

public class UserController {
	
	private UserService userservice;
	
	public UserController(UserService userservice) {
		this.userservice = userservice;
		
	}
	
	@PostMapping
	public ResponseEntity<UserDto> createPost(@Valid @RequestBody UserDto userdto)
	{
		ResponseEntity<UserDto> entity = null;
		if(userservice.ExistsByEmail(userdto))
		{
			throw new
			  ReservationApiException(HttpStatus.BAD_REQUEST,"Email user is Already exists ");
		}
		 else { 
		entity =new ResponseEntity<>(userservice.createUser(userdto),HttpStatus.CREATED);
		 }
		return entity;
	}
}
		
			
	


