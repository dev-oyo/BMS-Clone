package com.example.BookMyShow.Service.Impl;

import com.example.BookMyShow.Dto.UserDto;
import com.example.BookMyShow.Entity.UserEntity;
import com.example.BookMyShow.Exception.BadReqException;
import com.example.BookMyShow.Exception.NotFoundException;
import com.example.BookMyShow.Repository.UserRepository;
import com.example.BookMyShow.Service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() throws RuntimeException
    {
        try
        {
            List<UserDto> userDtoList = new ArrayList<>();
            for (UserEntity user : userRepository.findAll()) {
                userDtoList.add(convertToDto(user));
            }
            return userDtoList;
        }
        catch (Exception e)
        {
            throw new RuntimeException("Unknown Error occurred!");
        }
    }

    @Override
    public UserDto createUser(UserDto userDto) throws RuntimeException
    {
        try
        {
            UserEntity user = convertToEntity(userDto);
            UserEntity newUser = new UserEntity(user.getName(), user.getEmail(), user.getWalletBalance());
            userRepository.save(newUser);
            return convertToDto(newUser);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Unknown Error occurred!");
        }
    }

    // Get User Details
    @Override
    public UserDto getDetails(String id) throws RuntimeException, NotFoundException
    {
        try
        {
            return convertToDto(userRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("User not found!")));
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
    public Double updateWalletBalance(String id, Double amount) throws NotFoundException, RuntimeException, BadReqException
    {
        try {
            if(amount<=0)
                throw new BadReqException("Amount can't be negative!");
            UserEntity userEntity = userRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("User not found!"));
            userEntity.setWalletBalance(userEntity.getWalletBalance() + amount);
            userRepository.save(userEntity);
            return userEntity.getWalletBalance();
        }
        catch (NotFoundException e)
        {
            throw new NotFoundException(e.getMessage());
        }
        catch(BadReqException e)
        {
            throw new BadReqException(e.getMessage());
        }
        catch (Exception e)
        {
            throw new RuntimeException("Unknown Error occurred!");
        }
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

