package com.restApi.springrest.repository;

import com.restApi.springrest.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<Users, ObjectId> {
    Users findByUserName(String username);
    void deleteByUserName(String username);
}
