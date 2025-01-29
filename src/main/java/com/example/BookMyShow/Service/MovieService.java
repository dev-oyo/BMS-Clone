package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dto.MovieDto;
import com.example.BookMyShow.Entity.Movie;

import java.util.List;

public interface MovieService {

    MovieDto addMovie(MovieDto movie, String theatre_id) throws RuntimeException;

    List<MovieDto> getAllMovies(String Id) throws RuntimeException;

    Movie convertToEntity(MovieDto movieDto);

    MovieDto convertToDto(Movie movie);
}
