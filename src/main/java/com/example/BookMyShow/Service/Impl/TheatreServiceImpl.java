package com.example.BookMyShow.Service.Impl;

import com.example.BookMyShow.Dto.TheatreDto;
import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Entity.Theatre;
import com.example.BookMyShow.Repository.BookingRepository;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.Repository.TheatreRepository;
import com.example.BookMyShow.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreServiceImpl implements TheatreService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheatreRepository theatreRepository;


    @Override
    public List<Booking> getAllBookings(String theatre_id)
    {
        List<Booking> filteredBookings = new ArrayList<>();
        for (Booking booking : bookingRepository.findAll())
        {
            if (booking.getShow().getMovie().getTheatre().getId().equals(theatre_id))
            {
                filteredBookings.add(booking);
            }
        }
        return filteredBookings;
    }

    @Override
    public Theatre addTheatre(Theatre theatre)
    {
        return theatreRepository.save(theatre);
    }

    @Override
    public List<Theatre> getTheatres()
    {
        return theatreRepository.findAll();
    }

    @Override
    public Theatre convertToEntity(TheatreDto theatreDto)
    {
        return new Theatre(theatreDto.getName(), theatreDto.getCity());
    }

    @Override
    public TheatreDto convertToDto(Theatre theatre)
    {
        return new TheatreDto(theatre.getName(), theatre.getCity());
    }
}
