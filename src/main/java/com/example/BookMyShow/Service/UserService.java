package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dto.UserDto;
import com.example.BookMyShow.Entity.UserEntity;
import com.example.BookMyShow.Exception.NotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public interface UserService {
    Mono<List<UserDto>> getAllUsers() throws RuntimeException;

    Mono<UserDto> createUser(UserDto userEntity) throws RuntimeException;

    Mono<UserDto> updateWalletBalance(String id, Double amount) throws RuntimeException;

    Mono<UserDto> getDetails(String id) throws NotFoundException, RuntimeException;

    UserEntity convertToEntity(UserDto userDto);

    UserDto convertToDto(UserEntity userEntity);
}
