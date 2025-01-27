package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Entity.UserEntity;
import com.example.BookMyShow.Service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    // Get User Details
    @GetMapping("/getDetails")
    public UserEntity getDetails(@RequestHeader("user_id") String Id)
    {
        return userServiceImpl.getDetails(Id);
    }

    // Create a new booking for a user
    @PostMapping("/createBooking")
    public ResponseEntity<?> createBooking(
            @RequestHeader("user_id") String user_id,
            @RequestBody Booking booking,
            @RequestHeader("show_id") String show_id)
    {
        return userServiceImpl.createBooking(user_id, booking.getTickets(),show_id);
    }

    // Get bookings for a user
    @GetMapping("/getBookings")
    public List<Booking> getBookingsByUser(@RequestHeader("user_id") String Id) {
        return userServiceImpl.getBookingsByUser(Id);
    }

    // Recharge Wallet
    @PutMapping("/rechargeWallet")
    public Double updateWalletBalance(@RequestBody Double Amount,@RequestHeader("user_id") String Id)
    {
        return userServiceImpl.updateWalletBalance(Id,Amount);
    }
}
