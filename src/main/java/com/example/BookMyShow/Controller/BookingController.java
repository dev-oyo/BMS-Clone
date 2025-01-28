package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Dto.BookingDto;
import com.example.BookMyShow.Exception.BadReqException;
import com.example.BookMyShow.Exception.NotFoundException;
import com.example.BookMyShow.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingServiceImpl;

    // Create a new booking for a user
    @PostMapping("/createBooking")
    public ResponseEntity<?> createBooking(
            @RequestParam("user_id") String user_id,
            @RequestBody BookingDto bookingDto,
            @RequestParam("show_id") String show_id) throws NotFoundException, BadReqException, RuntimeException
    {
        return bookingServiceImpl.createBooking(user_id, bookingDto,show_id);
    }

    // Get bookings for a user
    @GetMapping("/getBookings")
    public List<BookingDto> getBookingsByUser(@RequestParam("user_id") String Id) throws NotFoundException, RuntimeException
    {
        return bookingServiceImpl.getBookingsByUser(Id);
    }
}
