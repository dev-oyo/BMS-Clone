package com.example.BookMyShow.Service.Impl;

import com.example.BookMyShow.Dto.BookingDto;
import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Entity.Show;
import com.example.BookMyShow.Entity.UserEntity;
import com.example.BookMyShow.Exception.BadReqException;
import com.example.BookMyShow.Exception.NotFoundException;
import com.example.BookMyShow.Repository.BookingRepository;
import com.example.BookMyShow.Repository.ShowRepository;
import com.example.BookMyShow.Repository.UserRepository;
import com.example.BookMyShow.Service.BookingService;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.*;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowRepository showRepository;

    // Create new booking
    @Override
    public ResponseEntity<?> createBooking(String Id, BookingDto bookingDto, String show_id) throws NotFoundException, BadReqException, RuntimeException{
        try
        {
            Long tickets = bookingDto.getTickets();
            UserEntity userEntity = userRepository.findById(Id)
                    .orElseThrow(() -> new NotFoundException("User not found!"));
            Show show = showRepository.findById(show_id)
                    .orElseThrow(() -> new NotFoundException("Show not found!"));
            if (tickets <= 0)
            {
                throw new BadReqException("Invalid Input");
            }
            if (tickets > show.getCapacity())
            {
                throw new BadReqException("Seats not available!");
            }
            if (userEntity.getWalletBalance() >= tickets * show.getCost())
            {
                show.setCapacity(show.getCapacity() - tickets);
                showRepository.save(show);
                userEntity.setWalletBalance(userEntity.getWalletBalance() - tickets * show.getCost());
                userRepository.save(userEntity);
                Booking booking = new Booking(userEntity, tickets, show);
                bookingRepository.save(booking);
                return new ResponseEntity<>(convertToDto(booking), HttpStatus.OK);
            }
            else
            {
                throw new BadReqException("Insufficient Balance!");
            }
        }
        catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        catch (BadReqException e) {
            throw new BadReqException(e.getMessage());
        }
        catch (Exception e) {
            throw new RuntimeException("Unknown Error Occurred");
        }
    }

    // Get bookings of a user
    @Override
    public List<BookingDto> getBookingsByUser(String Id) throws NotFoundException, RuntimeException{
        try
        {
            UserEntity userEntity = userRepository.findById(Id)
                    .orElseThrow(() -> new NotFoundException("User not found!"));
            List<BookingDto> bookingDtoList = new ArrayList<>();
            for (Booking booking : userEntity.getBookings())
            {
                bookingDtoList.add(convertToDto(booking));
            }
            return bookingDtoList;
        }
        catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        catch (Exception e) {
            throw new RuntimeException("Unknown error occurred!");
        }
    }

    @Override
    public BookingDto convertToDto(Booking booking)
    {
        return new BookingDto(booking.getTickets(), booking.getTotal_cost());
    }

}