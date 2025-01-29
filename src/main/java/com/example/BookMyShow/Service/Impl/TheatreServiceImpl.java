package com.example.BookMyShow.Service.Impl;

import com.example.BookMyShow.Dto.TheatreDto;
import com.example.BookMyShow.Entity.Theatre;
import com.example.BookMyShow.Repository.BookingRepository;
import com.example.BookMyShow.Repository.TheatreRepository;
import com.example.BookMyShow.Service.BookingService;
import com.example.BookMyShow.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreServiceImpl implements TheatreService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private BookingService bookingServiceImpl;

    @Override
    public TheatreDto addTheatre(TheatreDto theatreDto) throws RuntimeException
    {
        try
        {
            Theatre theatre = convertToEntity(theatreDto);
            theatreRepository.save(theatre);
            return convertToDto(theatre);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Unknown error occurred!");
        }
    }

    @Override
    public List<TheatreDto> getTheatres() throws RuntimeException
    {
        try
        {
            List<TheatreDto> theatreDtoList = new ArrayList<>();
            for (Theatre theatre : theatreRepository.findAll()) {
                theatreDtoList.add(convertToDto(theatre));
            }
            return theatreDtoList;
        }
        catch (Exception e)
        {
            throw new RuntimeException("Unknown error occurred!");
        }
    }

    @Override
    public Theatre convertToEntity(TheatreDto theatreDto)
    {
        return new Theatre(theatreDto.getName(), theatreDto.getCity());
    }

    @Override
    public TheatreDto convertToDto(Theatre theatre)
    {
        return new TheatreDto(theatre.getName(), theatre.getCity());
    }
}
