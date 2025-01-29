package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dto.UserDto;
import com.example.BookMyShow.Entity.UserEntity;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UserService {
    Mono<List<UserDto>> getAllUsers() throws RuntimeException;

    Mono<UserDto> createUser(UserDto userEntity) throws RuntimeException;

    Mono<UserDto> updateWalletBalance(String id, Double amount) throws RuntimeException;

    Mono<UserDto> getDetails(String id) throws RuntimeException;

    UserEntity convertToEntity(UserDto userDto);

    UserDto convertToDto(UserEntity userEntity);
}
