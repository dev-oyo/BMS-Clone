package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Dto.BookingDto;
import com.example.BookMyShow.Response.BookingResponse;
import com.example.BookMyShow.Service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/booking")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    // Create a new booking for a user
    @PostMapping("/create")
    public ResponseEntity<?> createBooking(
            @RequestParam("user_id") String user_id,
            @RequestBody BookingDto bookingDto,
            @RequestParam("show_id") String show_id) throws RuntimeException {
        return bookingService.createBooking(user_id, bookingDto,show_id);
    }

    // Get bookings
    @GetMapping("/get")
    public List<BookingResponse> getBookingsByUser(
            @RequestParam Map<String,String> allParams) throws RuntimeException {
        return bookingService.getBookingsByUser(allParams);
    }
}
