package com.example.BookMyShow.Service.Impl;

import com.example.BookMyShow.Dto.TheatreDto;
import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Entity.Movie;
import com.example.BookMyShow.Entity.Theatre;
import com.example.BookMyShow.Exception.NotFoundException;
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
    public Movie addMovie(Movie movie, String theatre_id)
    {
        Theatre theatre = theatreRepository.findById(theatre_id).orElseThrow(() -> new NotFoundException("Theatre not found"));
        movie.setTheatre(theatre);
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies(String Id)
    {
        Theatre theatre = theatreRepository.findById(Id).orElseThrow(() -> new NotFoundException("Theatre not found"));
        return theatre.getMovies();
    }

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
