package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dto.MovieDto;
import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Entity.Movie;
import com.example.BookMyShow.Entity.Show;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {

    Movie addMovie(Movie movie, String theatre_id);

    List<Movie> getAllMovies(String Id);

    Movie convertToEntity(MovieDto movieDto);

    MovieDto convertToDto(Movie movie);
}
