package com.example.BookMyShow.Service;

import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Entity.Show;
import com.example.BookMyShow.Entity.UserEntity;
import com.example.BookMyShow.Exception.NotFoundException;
import com.example.BookMyShow.Repository.BookingRepository;
import com.example.BookMyShow.Repository.ShowRepository;
import com.example.BookMyShow.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowRepository showRepository;

    // Create new booking
    public ResponseEntity<?> createBooking(String Id, Long tickets, String show_id) {
        UserEntity userEntity = userRepository.findById(Id).orElseThrow(() -> new NotFoundException("User not found!"));
        Show show = showRepository.findById(show_id).orElseThrow(() -> new NotFoundException("Show not found!"));
        if(tickets<=0)
        {
            return new ResponseEntity<>("Invalid Input",HttpStatus.BAD_REQUEST);
        }
        if(tickets>show.getCapacity())
        {
            return new ResponseEntity<>("Seats not available!",HttpStatus.BAD_REQUEST);
        }
        if(userEntity.getWalletBalance()>=tickets*show.getCost())
        {
            show.setCapacity(show.getCapacity()-tickets);
            showRepository.save(show);
            userEntity.setWalletBalance(userEntity.getWalletBalance()-tickets*show.getCost());
            userRepository.save(userEntity);
            Booking booking = new Booking(userEntity, tickets, show);
            return new ResponseEntity<>(bookingRepository.save(booking), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Insufficient Balance!",HttpStatus.BAD_REQUEST);
        }
    }

    // Get bookings of a user
    public List<Booking> getBookingsByUser(String Id) {
        UserEntity userEntity = userRepository.findById(Id).orElseThrow(() -> new NotFoundException("User not found!"));
        return userEntity.getBookings();
    }

    // Recharge Wallet
    public Double updateWalletBalance(String id, Double amount) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found!"));
        userEntity.setWalletBalance(userEntity.getWalletBalance()+amount);
        userRepository.save(userEntity);
        return userEntity.getWalletBalance();
    }

    // Get User Details
    public UserEntity getDetails(String id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found!"));
    }

}