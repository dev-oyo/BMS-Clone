package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dto.MovieDto;
import com.example.BookMyShow.Entity.Movie;
import com.example.BookMyShow.Exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {

    MovieDto addMovie(MovieDto movie, String theatre_id) throws NotFoundException, RuntimeException;

    List<MovieDto> getAllMovies(String Id) throws NotFoundException, RuntimeException;

    Movie convertToEntity(MovieDto movieDto);

    MovieDto convertToDto(Movie movie);
}
