package com.spring.HotelBooking.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CurrentDateTime {
	
    public LocalDateTime localDateTime() {
        return LocalDateTime.now();
    }

    public LocalDate localDate() {
        return LocalDate.now();
    }

}
