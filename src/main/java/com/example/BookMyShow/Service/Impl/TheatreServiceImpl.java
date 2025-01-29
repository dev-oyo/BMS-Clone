package com.example.BookMyShow.Service.Impl;

import com.example.BookMyShow.Dto.TheatreDto;
import com.example.BookMyShow.Entity.Theatre;
import com.example.BookMyShow.Repository.TheatreRepository;
import com.example.BookMyShow.Service.TheatreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TheatreServiceImpl implements TheatreService {

    private final TheatreRepository theatreRepository;

    // Add theatre
    @Override
    public TheatreDto addTheatre(TheatreDto theatreDto) throws RuntimeException {
        try {
            Theatre theatre = convertToEntity(theatreDto);
            theatreRepository.save(theatre);
            return convertToDto(theatre);
        } catch (Exception e) {
            throw new RuntimeException("Unknown error occurred!");
        }
    }

    // Get theatres
    @Override
    public List<TheatreDto> getTheatres() throws RuntimeException {
        try {
            List<TheatreDto> theatreDtoList = new ArrayList<>();
            for (Theatre theatre : theatreRepository.findAll()) {
                theatreDtoList.add(convertToDto(theatre));
            }
            return theatreDtoList;
        } catch (Exception e) {
            throw new RuntimeException("Unknown error occurred!");
        }
    }

    @Override
    public Theatre convertToEntity(TheatreDto theatreDto) {
        return new Theatre(theatreDto.getName(), theatreDto.getCity());
    }

    @Override
    public TheatreDto convertToDto(Theatre theatre) {
        return new TheatreDto(theatre.getName(), theatre.getCity());
    }
}