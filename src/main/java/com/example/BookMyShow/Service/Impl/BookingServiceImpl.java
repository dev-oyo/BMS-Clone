package com.example.BookMyShow.Service.Impl;

import com.example.BookMyShow.Dto.BookingDto;
import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Entity.Show;
import com.example.BookMyShow.Entity.UserEntity;
import com.example.BookMyShow.Exception.BadReqException;
import com.example.BookMyShow.Exception.NotFoundException;
import com.example.BookMyShow.Repository.*;
import com.example.BookMyShow.Response.BookingResponse;
import com.example.BookMyShow.Service.BookingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheatreRepository theatreRepository;

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

    // Get bookings
    @Override
    public List<BookingResponse> getBookingsByUser(Map<String,String> allParams) throws NotFoundException, RuntimeException, BadReqException{
        try
        {
            if(allParams.size()!=1)
                throw new BadReqException("Invalid Input");
            List<BookingResponse> bookingResponseList = new ArrayList<>();
            if(allParams.get("user_id")!=null)
            {
                String userId=allParams.get("user_id");
                UserEntity userEntity = userRepository.findById(userId)
                        .orElseThrow(() -> new NotFoundException("User not found!"));
                for (Booking booking : userEntity.getBookings())
                {
                    bookingResponseList.add(convertToResponse(booking));
                }
                return bookingResponseList;
            }
            else if (allParams.get("show_id")!=null)
            {
                String showId=allParams.get("show_id");
                Show show = showRepository.findById(showId)
                        .orElseThrow(() -> new NotFoundException("Show not found"));
                for (Booking booking : show.getBookings())
                {
                    bookingResponseList.add(convertToResponse(booking));
                }
                return bookingResponseList;
            }
            else if (allParams.get("movie_id")!=null)
            {
                String movieId=allParams.get("movie_id");
                movieRepository.findById(movieId).orElseThrow(() -> new NotFoundException("Movie not found"));
                for (Booking booking : bookingRepository.findAll())
                {
                    if(booking.getShow().getMovie().getId().equals(movieId))
                    {
                        bookingResponseList.add(convertToResponse(booking));
                    }
                }
                return bookingResponseList;
            }
            else if (allParams.get("theatre_id")!=null)
            {
                String theatreId=allParams.get("theatre_id");
                theatreRepository.findById(theatreId).orElseThrow(() -> new NotFoundException("Theatre not found"));
                for (Booking booking : bookingRepository.findAll())
                {
                    if (booking.getShow().getMovie().getTheatre().getId().equals(theatreId))
                    {
                        bookingResponseList.add(convertToResponse(booking));
                    }
                }
                return bookingResponseList;
            }
            else
            {
                throw new BadReqException("Invalid Input");
            }
        }
        catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        catch (BadReqException e)
        {
            throw new BadReqException(e.getMessage());
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

    @Override
    public BookingResponse convertToResponse(Booking booking) {
        BookingResponse bookingResponse=new BookingResponse();
        bookingResponse.setTickets(booking.getTickets());
        bookingResponse.setTotalCost(booking.getTotal_cost());
        bookingResponse.setShowDate(booking.getShow().getDate());
        bookingResponse.setShowTime(booking.getShow().getTime());
        bookingResponse.setMovieName(booking.getShow().getMovie().getName());
        bookingResponse.setTheatreName(booking.getShow().getMovie().getTheatre().getName());
        return bookingResponse;
    }

}