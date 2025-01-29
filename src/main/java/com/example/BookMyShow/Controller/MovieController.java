package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Dto.MovieDto;
import com.example.BookMyShow.Exception.NotFoundException;
import com.example.BookMyShow.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieServiceImpl;

    // Get Movies
    @GetMapping("get")
    public List<MovieDto> getMovies(@RequestParam("theatre_id") String Id) throws NotFoundException, RuntimeException
    {
        return movieServiceImpl.getAllMovies(Id);
    }

    // Add a Movie
    @PostMapping("add")
    public MovieDto addMovie(@RequestBody MovieDto movie, @RequestParam("theatre_id") String theatre_id) throws NotFoundException, RuntimeException
    {
        return movieServiceImpl.addMovie(movie,theatre_id);
    }

}
