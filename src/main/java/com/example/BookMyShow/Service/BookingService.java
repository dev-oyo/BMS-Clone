package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dto.BookingDto;
import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Exception.BadReqException;
import com.example.BookMyShow.Exception.NotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService
{
    // Create new booking
    ResponseEntity<?> createBooking(String Id, BookingDto bookingDto, String show_id)
            throws NotFoundException, BadReqException, RuntimeException;

    // Get bookings of a user
    List<BookingDto> getBookingsByUser(String Id) throws NotFoundException, RuntimeException;

//    Booking convertToEntity(BookingDto bookingDto);

    BookingDto convertToDto(Booking booking);
}
