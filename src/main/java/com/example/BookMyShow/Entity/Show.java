package com.example.BookMyShow.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Shows")
@Data
@RequiredArgsConstructor
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Double cost;
    private String date;
    private String time;
    private Long capacity;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @OneToMany(mappedBy = "show")
    @JsonIgnore
    private List<Booking> bookings;

}
