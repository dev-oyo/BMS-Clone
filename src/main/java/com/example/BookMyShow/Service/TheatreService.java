package com.example.BookMyShow.Service;

import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Entity.Movie;
import com.example.BookMyShow.Entity.Theatre;
import com.example.BookMyShow.Exception.NotFoundException;
import com.example.BookMyShow.Repository.BookingRepository;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    public Movie addMovie(Movie movie,String theatre_id)
    {
        Theatre theatre = theatreRepository.findById(theatre_id).orElseThrow(() -> new NotFoundException("Theatre not found"));
        movie.setTheatre(theatre);
        return movieRepository.save(movie);
    }


    public List<Movie> getAllMovies(String Id)
    {
        Theatre theatre = theatreRepository.findById(Id).orElseThrow(() -> new NotFoundException("Theatre not found"));
        return theatre.getMovies();
    }

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
}
