package com.spring.HotelBooking.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.spring.HotelBooking.entity.Booking;
import com.spring.HotelBooking.entity.User;
import com.spring.HotelBooking.exception.ReservationApiException;
import com.spring.HotelBooking.exception.ResourceNotFoundException;
import com.spring.HotelBooking.payload.BookingDto;
import com.spring.HotelBooking.repository.BookingRepository;
import com.spring.HotelBooking.repository.UserRepository;
import com.spring.HotelBooking.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	private BookingRepository bookingrepository;

	private UserRepository userrepository;

	public BookingServiceImpl(BookingRepository bookingrepository, UserRepository userrepository) {
		super();
		this.bookingrepository = bookingrepository;
		this.userrepository = userrepository;
	}

	@Override
	public BookingDto createbooking(Long userid, BookingDto bookingdto) {
		// TODO Auto-generated method stub
		Booking booking = mapToEntity(bookingdto);

		User user = userrepository.findById(userid)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userid", userid));

		booking.setUser(user);
		Booking newbooking = bookingrepository.save(booking);

		return mapToDto(newbooking);

	}

//
	@Override
	public BookingDto updateStatus(Long userid, Long res_id, BookingDto request) {
		// TODO Auto-generated method stub
		User user = userrepository.findById(userid)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userid));
		Booking booking = bookingrepository.findById(res_id)
				.orElseThrow(() -> new ResourceNotFoundException("Booking", "id", res_id));

		if (!booking.getUser().getUserid().equals(user.getUserid())) {

			throw new ReservationApiException(HttpStatus.BAD_REQUEST, "Booking  does not belong to current User ");

		}
		booking.setStatus("Cancelled");
		Booking updatedbooking = bookingrepository.save(booking);
		BookingDto dto = new BookingDto();
		dto.setStatus(updatedbooking.getStatus());
		dto.setReservationId(booking.getReservationId());
		dto.setNo_guest(booking.getNo_guest());
		dto.setCheckInDate(booking.getCheckInDate());
		dto.setCheckOutDate(booking.getCheckOutDate());
		return dto;

	}


	  @Override public boolean existsCheckIn(BookingDto r) {
	 LocalDate checkIn,checkOut;
	 LocalDate dtocheckin=r.getCheckInDate();
	 LocalDate dtocheckout=r.getCheckOutDate();
	  boolean bool=true; 
	  List<Booking> bookinglist=bookingrepository.findAll();
	 for(Booking b:bookinglist) {
			
	 if(b.getStatus().equalsIgnoreCase("Booked")) {
	 
	  checkOut=b.getCheckOutDate();
	 checkIn=b.getCheckInDate();
	 
		/*
		 * boolean bool_1=(!(dtocheckin.isAfter(checkIn)) &&
		 * !(dtocheckin.isAfter(checkOut))); boolean
		 * bool_2=(dtocheckout.isBefore(checkIn) && (dtocheckout.isBefore(checkOut)));
		 * if(bool_1 || bool_2) 
		 */
	 
	  if((dtocheckout.isBefore(checkIn))|| (dtocheckin.isAfter(checkOut))) {
	  
	  bool=true;
	  }
	  
	  else { bool=false;}
	  
	  
	  } else { bool=false; }
	 }
	  
	  return bool;
	 
	  }
	 
	@Override
	public BookingDto getbookingByResId(Long userid, Long res_id) {

		User user = userrepository.findById(userid)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userid));

		// retrieve booking By reservation id
		Booking booking = bookingrepository.findById(res_id)
				.orElseThrow(() -> new ResourceNotFoundException("Booking", "id", res_id));

		if (!booking.getUser().getUserid().equals(user.getUserid())) {

			throw new ReservationApiException(HttpStatus.BAD_REQUEST, "Booking  does not belong to current User ");
		}

		return mapToDto(booking);

	}

	private BookingDto mapToDto(Booking booking) {
		BookingDto bookingdto = new BookingDto();
		// bookingdto.setId(booking.getId());
		bookingdto.setStatus(booking.getStatus());
		bookingdto.setReservationId(booking.getReservationId());
		bookingdto.setNo_guest(booking.getNo_guest());
		bookingdto.setCheckInDate(booking.getCheckInDate());
		bookingdto.setCheckOutDate(booking.getCheckOutDate());
		return bookingdto;

	}

	private Booking mapToEntity(BookingDto bookingdto) {
		Booking booking = new Booking();
		// booking.setId(bookingdto.getId());

		booking.setStatus("Booked");
		booking.setReservationId(bookingdto.getReservationId());
		booking.setNo_guest(bookingdto.getNo_guest());
		booking.setCheckInDate(bookingdto.getCheckInDate());
		booking.setCheckOutDate(bookingdto.getCheckOutDate());
		return booking;

	}

}
