package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Entity.Movie;
import com.example.BookMyShow.Service.Impl.TheatreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

    @Autowired
    private TheatreServiceImpl theatreServiceImpl;

    // Get Movies
    @GetMapping("getMovies")
    public List<Movie> getMovies(@RequestHeader("theatre_id") String Id)
    {
        return theatreServiceImpl.getAllMovies(Id);
    }

    // Add a Movie
    @PostMapping("addMovie")
    public Movie addMovie(@RequestBody Movie movie, @RequestHeader("theatre_id") String theatre_id)
    {
        return theatreServiceImpl.addMovie(movie,theatre_id);
    }

    // Get All Bookings
    @GetMapping("getBookings")
    public List<Booking> getAllBookings(@RequestHeader("theatre_id") String theatre_id)
    {
        return theatreServiceImpl.getAllBookings(theatre_id);
    }
}
