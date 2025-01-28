package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dto.BookingDto;
import com.example.BookMyShow.Dto.ShowDto;
import com.example.BookMyShow.Entity.Show;
import com.example.BookMyShow.Exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShowService {

    List<ShowDto> getALlShows(String movieId) throws RuntimeException;

    ShowDto addShow(String movieId, ShowDto show) throws NotFoundException, RuntimeException;

    Show convertToEntity(ShowDto showDto);

    ShowDto convertToDto(Show show);
}
