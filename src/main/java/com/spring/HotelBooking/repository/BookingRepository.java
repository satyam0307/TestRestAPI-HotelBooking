package com.spring.HotelBooking.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.HotelBooking.entity.Booking;
import com.spring.HotelBooking.payload.BookingDto;


public interface BookingRepository  extends JpaRepository<Booking ,Long>{

	
	 @Query(value = "UPDATE  Booking SET booking_status = "+"Cancelled"+" WHERE reservation_id = res_id;", nativeQuery = true)
	 List<Booking> updateStatus(Long userid, @PathVariable(value="res_id") Long res_id ,BookingDto bookingdto);
	//List<Booking> findByResId(long id);
	
	 @Query(value="SELECT check_in_date,check_out_date FROM booking\r\n" +
	 "where booking_status=\"Booked\"; ",nativeQuery=true) 
	boolean existsCheckIn(@PathVariable(value="booking_status") String status);
	 
	
	
}
