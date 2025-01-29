package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dto.ShowDto;
import com.example.BookMyShow.Entity.Show;

import java.util.List;

public interface ShowService {

    List<ShowDto> getALlShows(String movieId) throws RuntimeException;

    ShowDto addShow(String movieId, ShowDto show) throws RuntimeException;

    Show convertToEntity(ShowDto showDto);

    ShowDto convertToDto(Show show);
}
