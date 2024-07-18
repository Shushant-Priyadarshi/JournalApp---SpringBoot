package com.restApi.springrest.controller;

import com.restApi.springrest.entity.Users;
import com.restApi.springrest.services.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;

    @GetMapping
    public List<Users> getAllUsers(){
       return userServices.getUser();

    }

    @PostMapping
    public String saveUser(@RequestBody  Users user){
        userServices.saveUser(user);
        return "User Created!";
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody Users user,@PathVariable String userName){
       Users userIndb = userServices.findByUserName(userName);
       if(userIndb !=null){
           userIndb.setUserName(user.getUserName());
           userIndb.setPassword(user.getPassword());
           userServices.saveUser(userIndb);

       }
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

//    @DeleteMapping("/id/{userId}")
//    public String deleteUserById(@PathVariable ObjectId userId){
//         userServices.deleteUser(userId);
//         return "User Deleted!";
//    }

//    @GetMapping("/id/{userId}")
//        public Optional<Users> getById(@PathVariable ObjectId userId){
//            return userServices.getUserById(userId);
//
//    }

}


