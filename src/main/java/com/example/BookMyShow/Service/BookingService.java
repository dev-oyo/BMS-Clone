package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dto.BookingDto;
import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Response.BookingResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface BookingService {

    ResponseEntity<?> createBooking(String Id, BookingDto bookingDto, String show_id) throws RuntimeException;

    List<BookingResponse> getBookingsByUser(Map<String,String> allParams) throws RuntimeException;

    BookingDto convertToDto(Booking booking);

    BookingResponse convertToResponse(Booking booking);
}
