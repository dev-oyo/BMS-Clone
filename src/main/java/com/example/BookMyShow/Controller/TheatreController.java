package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Dto.TheatreDto;
import com.example.BookMyShow.Service.TheatreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatre")
@AllArgsConstructor
public class TheatreController {

    private final TheatreService theatreServiceImpl;

    // Add a Theatre
    @PostMapping("/add")
    public TheatreDto addTheatre(@RequestBody TheatreDto theatre) throws RuntimeException {
        return theatreServiceImpl.addTheatre(theatre);
    }

    // Get Theatres
    @GetMapping("/get")
    public List<TheatreDto> getTheatres() throws RuntimeException {
        return theatreServiceImpl.getTheatres();
    }
}
