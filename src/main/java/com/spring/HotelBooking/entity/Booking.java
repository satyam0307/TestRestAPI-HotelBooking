package com.spring.HotelBooking.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
		name="Booking"
		)
public class Booking {
	@Id
	private Long reservationId = getRandomNumberString();
	@Min(1)
	@Max(3)@NotNull
	private int no_guest;
	
	
	 @NotNull(message = "Check in date required")
	 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) 
	 private LocalDate checkInDate;
	  @NotNull(message = "Check out date required")
	 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	  private LocalDate checkOutDate;
	  
	  @Column(name="BookingStatus")
	  private String Status;
	
	 
	  @ManyToOne(fetch=FetchType.LAZY)
	  @JoinColumn(name="userid",nullable=false)
	  private User user;
	  
	  
	  public static Long  getRandomNumberString() {
		    // It will generate 6 digit random Number.
		    // from 0 to 999999
		    Random rnd = new Random();
		    long number = rnd.nextInt(999999);
		    return number;
	  }
	  @Override
	    public String toString() {
	        return "ReservationDates{" +
	                "checkInDate=" + checkInDate +
	                ", checkOutDate=" + checkOutDate +
	             
	                '}';
	  }
	
}
