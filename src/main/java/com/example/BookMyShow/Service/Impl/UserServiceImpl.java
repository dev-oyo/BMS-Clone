package com.example.BookMyShow.Service.Impl;

import com.example.BookMyShow.Dto.UserDto;
import com.example.BookMyShow.Entity.Theatre;
import com.example.BookMyShow.Entity.UserEntity;
import com.example.BookMyShow.Repository.TheatreRepository;
import com.example.BookMyShow.Repository.UserRepository;
import com.example.BookMyShow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    @Override
    public List<UserEntity> getAllUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public UserEntity createUser(UserEntity userEntity)
    {
        UserEntity users= new UserEntity(userEntity.getName(),userEntity.getEmail(),userEntity.getWalletBalance());
        return userRepository.save(users);
    }

    @Override
    public Theatre addTheatre(Theatre theatre)
    {
        return theatreRepository.save(theatre);
    }

    @Override
    public List<Theatre> getTheatres()
    {
        return theatreRepository.findAll();
    }

    @Override
    public UserEntity convertToEntity(UserDto userDto)
    {
        return new UserEntity(userDto.getName(), userDto.getEmail(), userDto.getWalletBalance());
    }

    @Override
    public UserDto convertToDto(UserEntity userEntity)
    {
        return new UserDto(userEntity.getName(), userEntity.getEmail(),userEntity.getWalletBalance());
    }

}

