package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dto.TheatreDto;
import com.example.BookMyShow.Entity.Theatre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TheatreService {

    TheatreDto addTheatre(TheatreDto theatre) throws RuntimeException;

    List<TheatreDto> getTheatres() throws RuntimeException;

    Theatre convertToEntity(TheatreDto TheatreDto);

    TheatreDto convertToDto(Theatre theatre);
}
