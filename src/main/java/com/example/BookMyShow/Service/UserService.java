package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dto.UserDto;
import com.example.BookMyShow.Entity.Theatre;
import com.example.BookMyShow.Entity.UserEntity;
import com.example.BookMyShow.Exception.BadReqException;
import com.example.BookMyShow.Exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDto> getAllUsers() throws RuntimeException;

    UserDto createUser(UserDto userEntity) throws RuntimeException;

    Double updateWalletBalance(String id, Double amount) throws RuntimeException;

    UserDto getDetails(String id) throws NotFoundException, RuntimeException;

    UserEntity convertToEntity(UserDto userDto);

    UserDto convertToDto(UserEntity userEntity);
}
