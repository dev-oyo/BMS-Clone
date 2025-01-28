package com.example.BookMyShow.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Table(name="Theatres")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NonNull
    private String name;
    @NonNull
    private String city;

    @OneToMany(mappedBy = "theatre")
    @JsonIgnore
    private List<Movie> movies;

}
