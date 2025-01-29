package com.example.BookMyShow.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse
{
    private Long tickets;
    private String showTime;
    private String showDate;
    private String movieName;
    private String theatreName;
    private Double totalCost;
}
