package com.example.BookMyShow.Service.Impl;

import com.example.BookMyShow.Dto.MovieDto;
import com.example.BookMyShow.Entity.Movie;
import com.example.BookMyShow.Entity.Theatre;
import com.example.BookMyShow.Exception.NotFoundException;
import com.example.BookMyShow.Repository.*;
import com.example.BookMyShow.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private ShowRepository showRepository;

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
    public Movie convertToEntity(MovieDto movieDto)
    {
        return new Movie(movieDto.getName(), movieDto.getDuration());
    }

    @Override
    public MovieDto convertToDto(Movie movie)
    {
        return new MovieDto(movie.getName(), movie.getDuration());
    }
}
