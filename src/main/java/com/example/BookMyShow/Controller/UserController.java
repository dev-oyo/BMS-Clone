package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Entity.Theatre;
import com.example.BookMyShow.Entity.UserEntity;
import com.example.BookMyShow.Service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    // Get Users
    @GetMapping("getUsers")
    public List<UserEntity> getUsers()
    {
        return userServiceImpl.getAllUsers();
    }

    // Create a new user
    @PostMapping("/addUser")
    public UserEntity createUser(@RequestBody UserEntity userEntity)
    {
        return userServiceImpl.createUser(userEntity);
    }

    // Recharge Wallet
    @PutMapping("/rechargeWallet")
    public Double updateWalletBalance(@RequestBody Double Amount,@RequestHeader("user_id") String Id)
    {
        return userServiceImpl.updateWalletBalance(Id,Amount);
    }

    // Get User Details
    @GetMapping("/getDetails")
    public UserEntity getDetails(@RequestHeader("user_id") String Id)
    {
        return userServiceImpl.getDetails(Id);
    }


}
