package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Entity.Movie;
import com.example.BookMyShow.Entity.Theatre;
import com.example.BookMyShow.Service.Impl.TheatreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

    @Autowired
    private TheatreServiceImpl theatreServiceImpl;

    // Add a Theatre
    @PostMapping("/addTheatre")
    public Theatre addTheatre(@RequestBody Theatre theatre)
    {
        return theatreServiceImpl.addTheatre(theatre);
    }

    // Get Theatres
    @GetMapping("/getTheatres")
    public List<Theatre> getTheatres()
    {
        return theatreServiceImpl.getTheatres();
    }

    // Get All Bookings
    @GetMapping("getBookings")
    public List<Booking> getAllBookings(@RequestHeader("theatre_id") String theatre_id)
    {
        return theatreServiceImpl.getAllBookings(theatre_id);
    }
}
