package com.example.BookMyShow.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingDto {
    private Long tickets;
    private Double total_cost;
}
