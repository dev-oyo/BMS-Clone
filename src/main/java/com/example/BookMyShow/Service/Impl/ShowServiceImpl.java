package com.example.BookMyShow.Service.Impl;

import com.example.BookMyShow.Dto.ShowDto;
import com.example.BookMyShow.Entity.Movie;
import com.example.BookMyShow.Entity.Show;
import com.example.BookMyShow.Exception.NotFoundException;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.Repository.ShowRepository;
import com.example.BookMyShow.Service.ShowService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ShowServiceImpl implements ShowService {

    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;

    // Add show
    public ShowDto addShow(String movieId, ShowDto showDto) throws RuntimeException {
        try {
            Show show = convertToEntity(showDto);
            Movie movie = movieRepository.findById(movieId)
                    .orElseThrow(() -> new NotFoundException("Movie not found"));
            show.setMovie(movie);
            showRepository.save(show);
            return convertToDto(show);
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Unknown error occurred!");
        }
    }

    // Get all shows for a movie
    @Override
    public List<ShowDto> getALlShows(String movieId) throws RuntimeException {
        try {
            List<ShowDto> filteredShows = new ArrayList<>();
            for (Show show : showRepository.findAll()) {
                if (show.getMovie().getId().equals(movieId)) {
                    filteredShows.add(convertToDto(show));
                }
            }
            return filteredShows;
        } catch (Exception e) {
            throw new RuntimeException("Unknown error occurred!");
        }
    }

    @Override
    public Show convertToEntity(ShowDto showDto) {
        return new Show(showDto.getCost(), showDto.getDate(), showDto.getTime(),showDto.getCapacity());
    }

    @Override
    public ShowDto convertToDto(Show show) {
        return new ShowDto(show.getCost(), show.getDate(), show.getTime(),show.getCapacity());
    }
}
