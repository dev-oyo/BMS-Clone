package com.example.BookMyShow.Service.Impl;

import com.example.BookMyShow.Dto.UserDto;
import com.example.BookMyShow.Entity.UserEntity;
import com.example.BookMyShow.Exception.BadReqException;
import com.example.BookMyShow.Exception.NotFoundException;
import com.example.BookMyShow.Repository.UserRepository;
import com.example.BookMyShow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<List<UserDto>> getAllUsers() throws RuntimeException {
        List<UserDto> userDtoList=new ArrayList<>();
        try
        {
            for(UserEntity userEntity : userRepository.findAll())
            {
                    userDtoList.add(convertToDto(userEntity));
            }
            return Mono.just(userDtoList);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Unknown Error occurred!");
        }
    }

    @Override
    public Mono<UserDto> createUser(UserDto userDto) {

        try
        {
            UserEntity user = convertToEntity(userDto);
            UserEntity newUser = new UserEntity(user.getName(), user.getEmail(), user.getWalletBalance());
            userRepository.save(newUser);
            return Mono.just(convertToDto(newUser));
        }
        catch (Exception e)
        {
            throw new RuntimeException("Unknown Error occurred!");
        }
    }

    // Get User Details
    @Override
    public Mono<UserDto> getDetails(String userId)
    {
        try
        {
            return Mono.just(convertToDto(userRepository.findById(userId)
                    .orElseThrow(()->new NotFoundException("User not found"))));
        }
        catch (NotFoundException e)
        {
            throw new NotFoundException(e.getMessage());
        }
        catch (Exception e)
        {
            throw new RuntimeException("Unknown Error occurred!");
        }
    }

    // Recharge Wallet
    @Override
    public Mono<UserDto> updateWalletBalance(String userId, Double amount) throws  RuntimeException
    {
        if(amount<=0)
            throw new BadReqException("Amount can't be negative!");
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException("User not found"));
        userEntity.setWalletBalance(userEntity.getWalletBalance() + amount);
        userRepository.save(userEntity);
        return Mono.just(convertToDto(userEntity));
    }

    @Override
    public UserEntity convertToEntity(UserDto userDto) {
        return new UserEntity(userDto.getName(), userDto.getEmail(), userDto.getWalletBalance());
    }

    @Override
    public UserDto convertToDto(UserEntity userEntity) {
        return new UserDto(userEntity.getName(), userEntity.getEmail(), userEntity.getWalletBalance());
    }

}

