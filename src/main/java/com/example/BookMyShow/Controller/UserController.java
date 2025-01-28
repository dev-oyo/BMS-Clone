package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Dto.UserDto;
import com.example.BookMyShow.Entity.UserEntity;
import com.example.BookMyShow.Exception.BadReqException;
import com.example.BookMyShow.Exception.NotFoundException;
import com.example.BookMyShow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    // Get Users
    @GetMapping("getUsers")
    public List<UserDto> getUsers() throws RuntimeException
    {
        return userServiceImpl.getAllUsers();
    }

    // Create a new user
    @PostMapping("/addUser")
    public UserDto createUser(@RequestBody UserDto userEntity) throws RuntimeException
    {
        return userServiceImpl.createUser(userEntity);
    }

    // Recharge Wallet
    @PutMapping("/rechargeWallet")
    public Double updateWalletBalance(@RequestHeader("Amount") Double Amount,@RequestParam("user_id") String Id)
            throws NotFoundException, RuntimeException, BadReqException
    {
        return userServiceImpl.updateWalletBalance(Id,Amount);
    }

    // Get User Details
    @GetMapping("/getDetails")
    public UserDto getDetails(@RequestParam("user_id") String Id)
            throws NotFoundException, RuntimeException
    {
        return userServiceImpl.getDetails(Id);
    }


}
