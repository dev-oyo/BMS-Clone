package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Entity.Show;
import com.example.BookMyShow.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    // Get all Shows
    @GetMapping("/getShows")
    public List<Show> getAllShows(@RequestHeader("movie_id") String movie_id)
    {
        return movieService.getALlShows(movie_id);
    }

    // Add a Show
    @PostMapping("/addShow")
    public Show addShow(@RequestHeader("movie_id") String movie_id,@RequestBody Show show)
    {
        return movieService.addShow(movie_id,show);
    }

    // Get Bookings for a show
    @GetMapping("/getBookings")
    public List<Booking> getBookingsByShow(@RequestHeader("show_id") String show_id)
    {
        return movieService.getBookingsByShow(show_id);
    }
}
