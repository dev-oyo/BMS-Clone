package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Service.Impl.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class BookingController {

    @Autowired
    private BookingServiceImpl bookingServiceImpl;

    // Create a new booking for a user
    @PostMapping("/createBooking")
    public ResponseEntity<?> createBooking(
            @RequestHeader("user_id") String user_id,
            @RequestBody Booking booking,
            @RequestHeader("show_id") String show_id)
    {
        return bookingServiceImpl.createBooking(user_id, booking.getTickets(),show_id);
    }

    // Get bookings for a user
    @GetMapping("/getBookings")
    public List<Booking> getBookingsByUser(@RequestHeader("user_id") String Id) {
        return bookingServiceImpl.getBookingsByUser(Id);
    }
}
