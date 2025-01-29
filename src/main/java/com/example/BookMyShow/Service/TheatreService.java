package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dto.TheatreDto;
import com.example.BookMyShow.Entity.Theatre;

import java.util.List;

public interface TheatreService {

    TheatreDto addTheatre(TheatreDto theatre) throws RuntimeException;

    List<TheatreDto> getTheatres() throws RuntimeException;

    Theatre convertToEntity(TheatreDto TheatreDto);

    TheatreDto convertToDto(Theatre theatre);
}
