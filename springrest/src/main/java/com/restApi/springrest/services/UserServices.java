package com.restApi.springrest.services;

import com.restApi.springrest.entity.Users;
import com.restApi.springrest.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServices {

    @Autowired
    private UserRepo userRepo;

    public void saveUser(Users user){
        userRepo.save(user);
    }

    public List<Users> getUser(){
        return userRepo.findAll();
    }

    public Optional<Users> getUserById(ObjectId id){
        return userRepo.findById(id);
    }

    public void deleteUser(ObjectId id){
        userRepo.deleteById(id);
    }

    public Users findByUserName(String userName){
        return userRepo.findByUserName(userName);
    }

}
