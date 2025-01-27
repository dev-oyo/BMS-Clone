package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dto.UserDto;
import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Entity.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService
{
    ResponseEntity<?> createBooking(String Id, Long tickets, String show_id);

    // Get bookings of a user
    List<Booking> getBookingsByUser(String Id);

    // Recharge Wallet
    Double updateWalletBalance(String id, Double amount);

    // Get User Details
    UserEntity getDetails(String id);

}
