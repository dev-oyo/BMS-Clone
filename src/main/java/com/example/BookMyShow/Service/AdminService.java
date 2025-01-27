package com.example.BookMyShow.Service;

import com.example.BookMyShow.Entity.Theatre;
import com.example.BookMyShow.Entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    List<UserEntity> getAllUsers();

    UserEntity createUser(UserEntity userEntity);

    Theatre addTheatre(Theatre theatre);

    List<Theatre> getTheatres();
}
