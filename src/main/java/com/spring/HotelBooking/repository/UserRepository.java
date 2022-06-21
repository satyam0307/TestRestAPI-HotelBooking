package com.spring.HotelBooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.HotelBooking.entity.User;

import com.spring.HotelBooking.payload.UserDto;


@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
	
	
	
}
