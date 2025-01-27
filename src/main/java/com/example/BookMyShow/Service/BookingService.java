package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dto.BookingDto;
import com.example.BookMyShow.Entity.Booking;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService
{
    ResponseEntity<?> createBooking(String Id, Long tickets, String show_id);

    // Get bookings of a user
    List<Booking> getBookingsByUser(String Id);

    Booking convertToEntity(BookingDto bookingDto);

    BookingDto convertToDto(Booking booking);
}
