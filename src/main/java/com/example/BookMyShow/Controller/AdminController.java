package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Entity.Theatre;
import com.example.BookMyShow.Entity.UserEntity;
import com.example.BookMyShow.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Get Users
    @GetMapping("getUsers")
    public List<UserEntity> getUsers()
    {
        return adminService.getAllUsers();
    }

    // Create a new user
    @PostMapping("/addUser")
    public UserEntity createUser(@RequestBody UserEntity userEntity)
    {
        return adminService.createUser(userEntity);
    }

    // Add a Theatre
    @PostMapping("/addTheatre")
    public Theatre addTheatre(@RequestBody Theatre theatre)
    {
        return adminService.addTheatre(theatre);
    }

    // Get Theatres
    @GetMapping("/getTheatres")
    public List<Theatre> getTheatres()
    {
        return adminService.getTheatres();
    }
}
