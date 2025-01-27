package com.example.BookMyShow.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShowDto {
    private Double cost;
    private String date;
    private String time;
    private Long capacity;
}
