package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dto.ShowDto;
import com.example.BookMyShow.Dto.UserDto;
import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Entity.Show;
import com.example.BookMyShow.Entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShowService {

    List<Show> getALlShows(String movieId);

    Show addShow(String movieId, Show show);

    List<Booking> getBookingsByShow(String showId);

    Show convertToEntity(ShowDto showDto);

    ShowDto convertToDto(Show show);
}
