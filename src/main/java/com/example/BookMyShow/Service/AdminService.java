package com.example.BookMyShow.Service;

import com.example.BookMyShow.Entity.Theatre;
import com.example.BookMyShow.Entity.UserEntity;
import com.example.BookMyShow.Repository.TheatreRepository;
import com.example.BookMyShow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    public List<UserEntity> getAllUsers()
    {
        return userRepository.findAll();
    }

    public UserEntity createUser(UserEntity userEntity)
    {
        UserEntity users= new UserEntity(userEntity.getName(),userEntity.getEmail(),userEntity.getWalletBalance());
        return userRepository.save(users);
    }

    public Theatre addTheatre(Theatre theatre)
    {
        return theatreRepository.save(theatre);
    }

    public List<Theatre> getTheatres()
    {
        return theatreRepository.findAll();
    }
}
