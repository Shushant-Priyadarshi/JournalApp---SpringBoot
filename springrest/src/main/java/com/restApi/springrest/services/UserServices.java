package com.restApi.springrest.services;

import com.restApi.springrest.entity.Users;
import com.restApi.springrest.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class UserServices {

    @Autowired
    private UserRepo userRepo;



    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveUser(Users user){
        userRepo.save(user);
    }
    public boolean saveNewUser(Users user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepo.save(user);
            return  true;
        }catch(Exception e){
            log.error("Error occured for : {}",user.getUserName(),e);
            return false;
        }

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

    public List<Users> getAll(){
        return userRepo.findAll();
    }
}
