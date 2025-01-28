package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Dto.BookingDto;
import com.example.BookMyShow.Dto.ShowDto;
import com.example.BookMyShow.Entity.Show;
import com.example.BookMyShow.Exception.NotFoundException;
import com.example.BookMyShow.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/show")
public class ShowController {

    @Autowired
    private ShowService showServiceImpl;

    // Get all Shows
    @GetMapping("/getShows")
    public List<ShowDto> getAllShows(@RequestParam("movie_id") String movie_id) throws RuntimeException
    {
        return showServiceImpl.getALlShows(movie_id);
    }

    // Add a Show
    @PostMapping("/addShow")
    public ShowDto addShow(@RequestParam("movie_id") String movie_id,@RequestBody ShowDto show) throws NotFoundException, RuntimeException
    {
        return showServiceImpl.addShow(movie_id,show);
    }

    // Get Bookings for a show
    @GetMapping("/getBookings")
    public List<BookingDto> getBookingsByShow(@RequestParam("show_id") String show_id) throws RuntimeException
    {
        return showServiceImpl.getBookingsByShow(show_id);
    }
}
