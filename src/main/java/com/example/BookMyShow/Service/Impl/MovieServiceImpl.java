package com.example.BookMyShow.Service.Impl;

import com.example.BookMyShow.Dto.BookingDto;
import com.example.BookMyShow.Dto.MovieDto;
import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Entity.Movie;
import com.example.BookMyShow.Entity.Theatre;
import com.example.BookMyShow.Exception.BadReqException;
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

    @Autowired
    private TheatreRepository theatreRepository;

    @Override
    public MovieDto addMovie(MovieDto moviedto, String theatre_id) throws NotFoundException, RuntimeException
    {
        try
        {
            Movie movie = convertToEntity(moviedto);
            Theatre theatre = theatreRepository.findById(theatre_id)
                    .orElseThrow(() -> new NotFoundException("Theatre not found"));
            movie.setTheatre(theatre);
            movieRepository.save(movie);
            return convertToDto(movie);
        }
        catch (NotFoundException e)
        {
            throw new NotFoundException(e.getMessage());
        }
        catch (Exception e){
            throw new RuntimeException("Unknown error occurred!");
        }
    }

    @Override
    public List<MovieDto> getAllMovies(String Id) throws NotFoundException, RuntimeException
    {
        try
        {
            Theatre theatre = theatreRepository.findById(Id)
                    .orElseThrow(() -> new NotFoundException("Theatre not found"));
            List<MovieDto> movieDtoList = new ArrayList<>();
            for (Movie movie : theatre.getMovies()) {
                movieDtoList.add(convertToDto(movie));
            }
            return movieDtoList;
        }
        catch (NotFoundException e)
        {
            throw new NotFoundException(e.getMessage());
        }
        catch (Exception e)
        {
            throw new RuntimeException("Unknown error occurred!");
        }
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
