package com.example.BookMyShow.Repository;

import com.example.BookMyShow.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, String> {

}
