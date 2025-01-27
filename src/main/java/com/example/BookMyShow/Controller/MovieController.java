package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Entity.Movie;
import com.example.BookMyShow.Service.Impl.MovieServiceImpl;
import com.example.BookMyShow.Service.Impl.ShowServiceImpl;
import com.example.BookMyShow.Service.Impl.TheatreServiceImpl;
import com.example.BookMyShow.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieServiceImpl movieServiceImpl;

    @Autowired
    private ShowServiceImpl showServiceImpl;

    // Get Movies
    @GetMapping("getMovies")
    public List<Movie> getMovies(@RequestHeader("theatre_id") String Id)
    {
        return movieServiceImpl.getAllMovies(Id);
    }

    // Add a Movie
    @PostMapping("addMovie")
    public Movie addMovie(@RequestBody Movie movie, @RequestHeader("theatre_id") String theatre_id)
    {
        return movieServiceImpl.addMovie(movie,theatre_id);
    }

    // Get Bookings for a show
    @GetMapping("/getBookings")
    public List<Booking> getBookingsByShow(@RequestHeader("show_id") String show_id)
    {
        return showServiceImpl.getBookingsByShow(show_id);
    }
}
