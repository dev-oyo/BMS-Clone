package com.example.BookMyShow.Service.Impl;

import com.example.BookMyShow.Dto.UserDto;
import com.example.BookMyShow.Entity.UserEntity;
import com.example.BookMyShow.Exception.NotFoundException;
import com.example.BookMyShow.Repository.UserRepository;
import com.example.BookMyShow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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

    // Get User Details
    @Override
    public UserEntity getDetails(String id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found!"));
    }

    // Recharge Wallet
    @Override
    public Double updateWalletBalance(String id, Double amount) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found!"));
        userEntity.setWalletBalance(userEntity.getWalletBalance()+amount);
        userRepository.save(userEntity);
        return userEntity.getWalletBalance();
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

