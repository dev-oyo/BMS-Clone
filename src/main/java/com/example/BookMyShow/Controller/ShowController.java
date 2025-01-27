package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Entity.Show;
import com.example.BookMyShow.Service.Impl.ShowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShowController {

    @Autowired
    private ShowServiceImpl showServiceImpl;

    // Get all Shows
    @GetMapping("/getShows")
    public List<Show> getAllShows(@RequestHeader("movie_id") String movie_id)
    {
        return showServiceImpl.getALlShows(movie_id);
    }

    // Add a Show
    @PostMapping("/addShow")
    public Show addShow(@RequestHeader("movie_id") String movie_id,@RequestBody Show show)
    {
        return showServiceImpl.addShow(movie_id,show);
    }

}
