package com.example.BookMyShow.Repository;

import com.example.BookMyShow.Entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, String> {
}
