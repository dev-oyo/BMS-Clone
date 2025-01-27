package com.example.BookMyShow.Service;

import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TheatreService {

    Movie addMovie(Movie movie, String theatre_id);

    List<Movie> getAllMovies(String Id);

    List<Booking> getAllBookings(String theatre_id);
}
