package com.example.BookMyShow.Repository;

import com.example.BookMyShow.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, String> {

}
