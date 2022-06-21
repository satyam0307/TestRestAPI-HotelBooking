package com.spring.HotelBooking.service;

import java.time.LocalDate;
import java.util.List;

import com.spring.HotelBooking.payload.BookingDto;


public interface BookingService {
	
	BookingDto createbooking(Long userid, BookingDto bookingdto);
	BookingDto  updateStatus(Long res_id,Long userid,BookingDto request);
	BookingDto getbookingByResId(Long userid,Long res_id);
	//BookingDto checkBookingTime(LocalDate checkin, LocalDate checkout);
	boolean existsCheckIn(BookingDto r);


}
