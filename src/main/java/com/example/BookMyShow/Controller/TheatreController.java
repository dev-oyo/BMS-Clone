package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Dto.BookingDto;
import com.example.BookMyShow.Dto.TheatreDto;
import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Entity.Theatre;
import com.example.BookMyShow.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

    @Autowired
    private TheatreService theatreServiceImpl;

    // Add a Theatre
    @PostMapping("/addTheatre")
    public TheatreDto addTheatre(@RequestBody TheatreDto theatre) throws RuntimeException
    {
        return theatreServiceImpl.addTheatre(theatre);
    }

    // Get Theatres
    @GetMapping("/getTheatres")
    public List<TheatreDto> getTheatres() throws RuntimeException
    {
        return theatreServiceImpl.getTheatres();
    }
}
