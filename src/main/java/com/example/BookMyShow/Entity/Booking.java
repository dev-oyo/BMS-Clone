package com.example.BookMyShow.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "Bookings")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NonNull
    private Long tickets;
    private Double total_cost;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "show_id",nullable = false)
    private Show show;


    public Booking(UserEntity userEntity, Long tickets, Show show) {
        this.userEntity=userEntity;
        this.tickets=tickets;
        this.show=show;
        this.total_cost=tickets*show.getCost();
    }
}
