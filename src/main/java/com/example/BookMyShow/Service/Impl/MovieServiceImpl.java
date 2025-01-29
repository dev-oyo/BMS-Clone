package com.example.BookMyShow.Service.Impl;

import com.example.BookMyShow.Dto.MovieDto;
import com.example.BookMyShow.Entity.Movie;
import com.example.BookMyShow.Entity.Theatre;
import com.example.BookMyShow.Exception.NotFoundException;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.Repository.TheatreRepository;
import com.example.BookMyShow.Service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final TheatreRepository theatreRepository;

    // Add a movie
    @Override
    public MovieDto addMovie(MovieDto moviedto, String theatre_id) throws RuntimeException {
        try {
            Movie movie = convertToEntity(moviedto);
            Theatre theatre = theatreRepository.findById(theatre_id)
                    .orElseThrow(() -> new NotFoundException("Theatre not found"));
            movie.setTheatre(theatre);
            movieRepository.save(movie);
            return convertToDto(movie);
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException("Unknown error occurred!");
        }
    }

    // Get all movies for a theatre
    @Override
    public List<MovieDto> getAllMovies(String Id) throws RuntimeException {
        try {
            Theatre theatre = theatreRepository.findById(Id)
                    .orElseThrow(() -> new NotFoundException("Theatre not found"));
            List<MovieDto> movieDtoList = new ArrayList<>();
            for (Movie movie : theatre.getMovies()) {
                movieDtoList.add(convertToDto(movie));
            }
            return movieDtoList;
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Unknown error occurred!");
        }
    }

    @Override
    public Movie convertToEntity(MovieDto movieDto) {
        return new Movie(movieDto.getName(), movieDto.getDuration());
    }

    @Override
    public MovieDto convertToDto(Movie movie) {
        return new MovieDto(movie.getName(), movie.getDuration());
    }
}
