package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Dto.UserDto;
import com.example.BookMyShow.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // Get Users
    @GetMapping("get")
    public Mono<List<UserDto>> getUsers() throws RuntimeException {
        return userService.getAllUsers();
    }

    // Create a new user
    @PostMapping("/add")
    public Mono<UserDto> createUser(@RequestBody UserDto userEntity) throws RuntimeException {
        return userService.createUser(userEntity);
    }

    // Recharge Wallet
    @PutMapping("/rechargeWallet")
    public Mono<UserDto> updateWalletBalance(
            @RequestHeader("Amount") Double Amount,
            @RequestParam("user_id") String Id) throws RuntimeException {
        return userService.updateWalletBalance(Id,Amount);
    }

    // Get User Details
    @GetMapping("/getDetails")
    public Mono<UserDto> getDetails(@RequestParam("user_id") String Id) throws RuntimeException {
        return userService.getDetails(Id);
    }
}
