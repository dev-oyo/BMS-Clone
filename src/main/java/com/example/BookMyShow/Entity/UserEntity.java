package com.example.BookMyShow.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="Users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String email;
    private Double walletBalance ;

    @OneToMany(mappedBy = "userEntity")
    private List<Booking> bookings;

    public UserEntity(String name, String email, Double walletBalance) {
        this.name=name;
        this.email=email;
        if(walletBalance!=null)
            this.walletBalance=walletBalance;
        else
            this.walletBalance=0.0;
    }
}
