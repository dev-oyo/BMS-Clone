package com.example.BookMyShow.Service.Impl;

import com.example.BookMyShow.Dto.MovieDto;
import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Entity.Movie;
import com.example.BookMyShow.Entity.Show;
import com.example.BookMyShow.Exception.NotFoundException;
import com.example.BookMyShow.Repository.*;
import com.example.BookMyShow.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Show> getALlShows(String movieId) {
        List<Show> filteredShows = new ArrayList<>();
        for (Show show : showRepository.findAll()) {
            if (show.getMovie().getId().equals(movieId)) {
                filteredShows.add(show);
            }
        }
        return filteredShows;
    }

    @Override
    public Show addShow(String movieId, Show show) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new NotFoundException("Movie not found"));
        show.setMovie(movie);
        return showRepository.save(show);
    }

    @Override
    public List<Booking> getBookingsByShow(String showId) {
        Show show = showRepository.findById(showId).orElseThrow(() -> new NotFoundException("Show not found"));
        return show.getBookings();
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
