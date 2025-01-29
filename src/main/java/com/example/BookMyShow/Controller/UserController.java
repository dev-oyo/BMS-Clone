package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Dto.UserDto;
import com.example.BookMyShow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    // Get Users
    @GetMapping("get")
    public Mono<List<UserDto>> getUsers() throws RuntimeException
    {
        return userServiceImpl.getAllUsers();
    }

    // Create a new user
    @PostMapping("/add")
    public Mono<UserDto> createUser(@RequestBody UserDto userEntity) throws RuntimeException
    {
        return userServiceImpl.createUser(userEntity);
    }

    // Recharge Wallet
    @PutMapping("/rechargeWallet")
    public Mono<UserDto> updateWalletBalance(@RequestHeader("Amount") Double Amount, @RequestParam("user_id") String Id)
            throws RuntimeException
    {
        return userServiceImpl.updateWalletBalance(Id,Amount);
    }

    // Get User Details
    @GetMapping("/getDetails")
    public Mono<UserDto> getDetails(@RequestParam("user_id") String Id)
            throws RuntimeException
    {
        return userServiceImpl.getDetails(Id);
    }
}
