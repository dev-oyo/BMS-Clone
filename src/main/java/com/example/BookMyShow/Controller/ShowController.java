package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Dto.ShowDto;
import com.example.BookMyShow.Service.ShowService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/show")
@AllArgsConstructor
public class ShowController {

    private final ShowService showService;

    // Get all Shows
    @GetMapping("/get")
    public List<ShowDto> getAllShows(@RequestParam("movie_id") String movie_id) throws RuntimeException {
        return showService.getALlShows(movie_id);
    }

    // Add a Show
    @PostMapping("/add")
    public ShowDto addShow(
            @RequestParam("movie_id") String movie_id,
            @RequestBody ShowDto show) throws RuntimeException {
        return showService.addShow(movie_id,show);
    }
}
