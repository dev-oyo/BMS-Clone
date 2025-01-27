package com.example.BookMyShow.Service;

import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Entity.Movie;
import com.example.BookMyShow.Entity.Show;
import com.example.BookMyShow.Exception.NotFoundException;
import com.example.BookMyShow.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;


    public List<Show> getALlShows(String movieId) {
        List<Show> filteredShows = new ArrayList<>();
        for (Show show : showRepository.findAll()) {
            if (show.getMovie().getId().equals(movieId)) {
                filteredShows.add(show);
            }
        }
        return filteredShows;
    }

    public Show addShow(String movieId, Show show) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new NotFoundException("Movie not found"));
        show.setMovie(movie);
        return showRepository.save(show);
    }

    public List<Booking> getBookingsByShow(String showId) {
        Show show = showRepository.findById(showId).orElseThrow(() -> new NotFoundException("Show not found"));
        return show.getBookings();
    }
}
