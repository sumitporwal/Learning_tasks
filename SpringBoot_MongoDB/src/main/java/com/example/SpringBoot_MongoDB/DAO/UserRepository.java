package com.example.SpringBoot_MongoDB.DAO;

import com.example.SpringBoot_MongoDB.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findOneByUserId(String userId);
}
