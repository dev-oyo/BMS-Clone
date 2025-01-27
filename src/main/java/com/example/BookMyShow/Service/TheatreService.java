package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dto.TheatreDto;
import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Entity.Movie;
import com.example.BookMyShow.Entity.Theatre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TheatreService {

    Theatre addTheatre(Theatre theatre);

    List<Theatre> getTheatres();

    List<Booking> getAllBookings(String theatre_id);

    Theatre convertToEntity(TheatreDto TheatreDto);

    TheatreDto convertToDto(Theatre theatre);
}
