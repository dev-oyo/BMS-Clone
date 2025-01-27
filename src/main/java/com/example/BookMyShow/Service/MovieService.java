package com.example.BookMyShow.Service;

import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Entity.Show;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    List<Show> getALlShows(String movieId);

    Show addShow(String movieId, Show show);

    List<Booking> getBookingsByShow(String showId);
}
