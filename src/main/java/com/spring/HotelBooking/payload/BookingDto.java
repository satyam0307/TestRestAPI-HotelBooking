package com.spring.HotelBooking.payload;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.spring.HotelBooking.entity.Booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class BookingDto {
	
	 private Long reservationId=Booking.getRandomNumberString();
	@Min(1)
	@Max(3)
	private int no_guest;
	 @NotNull(message = "Check in date required")
	 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) 
	 private LocalDate checkInDate;
	  @NotNull(message = "Check out date required")
	 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	  private LocalDate checkOutDate;
	  @NotNull
	  private String Status;
	

}
