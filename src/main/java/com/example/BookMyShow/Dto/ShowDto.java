package com.example.BookMyShow.Dto;

import lombok.Data;

@Data
public class ShowDto {
    private Double cost;
    private String date;
    private String time;
    private Long capacity;
}
