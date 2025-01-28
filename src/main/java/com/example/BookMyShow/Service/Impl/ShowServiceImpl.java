package com.example.BookMyShow.Service.Impl;

import com.example.BookMyShow.Dto.BookingDto;
import com.example.BookMyShow.Dto.ShowDto;
import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Entity.Movie;
import com.example.BookMyShow.Entity.Show;
import com.example.BookMyShow.Exception.NotFoundException;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.Repository.ShowRepository;
import com.example.BookMyShow.Service.BookingService;
import com.example.BookMyShow.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowServiceImpl implements ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private BookingService bookingServiceImpl;

    @Override
    public ShowDto addShow(String movieId, ShowDto showDto) throws NotFoundException, RuntimeException
    {
        try
        {
            Show show = convertToEntity(showDto);
            Movie movie = movieRepository.findById(movieId)
                    .orElseThrow(() -> new NotFoundException("Movie not found"));
            show.setMovie(movie);
            showRepository.save(show);
            return convertToDto(show);
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
    public List<ShowDto> getALlShows(String movieId) throws RuntimeException
    {
        try
        {
            List<ShowDto> filteredShows = new ArrayList<>();
            for (Show show : showRepository.findAll())
            {
                if (show.getMovie().getId().equals(movieId))
                {
                    filteredShows.add(convertToDto(show));
                }
            }
            return filteredShows;
        }
        catch (Exception e)
        {
            throw new RuntimeException("Unknown error occurred!");
        }
    }

    @Override
    public List<BookingDto> getBookingsByShow(String showId) throws RuntimeException
    {
        try
        {
            Show show = showRepository.findById(showId)
                    .orElseThrow(() -> new NotFoundException("Show not found"));
            List<BookingDto> bookingDtoList = new ArrayList<>();
            for (Booking booking : show.getBookings())
            {
                bookingDtoList.add(bookingServiceImpl.convertToDto(booking));
            }
            return bookingDtoList;
        }
        catch (Exception e)
        {
            throw new RuntimeException("Unknown error occurred!");
        }
    }

    @Override
    public Show convertToEntity(ShowDto showDto)
    {
        return new Show(showDto.getCost(), showDto.getDate(), showDto.getTime(),showDto.getCapacity());
    }

    @Override
    public ShowDto convertToDto(Show show)
    {
        return new ShowDto(show.getCost(), show.getDate(), show.getTime(),show.getCapacity());
    }
}
