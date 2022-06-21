package com.spring.HotelBooking.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class UserDto {
	
	private Long id;
	@Email
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String fname;
	
	@NotEmpty
	private String lname;

}
