package com.spring.HotelBooking.service;

import com.spring.HotelBooking.payload.UserDto;


public interface UserService {
	
	UserDto createUser(UserDto userdto);
	Boolean ExistsByEmail(UserDto  email);
	

}
