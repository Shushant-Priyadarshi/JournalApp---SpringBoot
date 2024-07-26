package com.restApi.springrest.controller;

import com.restApi.springrest.entity.Users;
import com.restApi.springrest.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/health")
    public String getHealth(){
        return "Server is Running!";
    }


    @PostMapping("/create-user")
    public String saveUser(@RequestBody Users user){
        userServices.saveNewUser((user));
        return "User Created!";
    }
}
