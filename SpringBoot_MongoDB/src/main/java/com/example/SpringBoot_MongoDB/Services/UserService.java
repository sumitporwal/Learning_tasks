package com.example.SpringBoot_MongoDB.Services;

import com.example.SpringBoot_MongoDB.DAO.UserRepository;
import com.example.SpringBoot_MongoDB.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUser(String userId){
        User user = userRepository.findOneByUserId(userId);
        return user;
    }

    public User addUser(User user){
        userRepository.save(user);
        return user;
    }

    public String editUser(String userId,User user1){
        User user = userRepository.findOneByUserId(userId);
        String previousName = user.getName();
        if(user!=null){
            user.setName(user1.getName());
            user.setEmail(user1.getEmail());
            user.setAge(user1.getAge());
            userRepository.save(user);
            return "user " + user.getName() + " updated";
        }
        return "user not found";
    }

    public User deleteUser(String userId){
        User user = userRepository.findOneByUserId(userId);
        String previousName = user.getName();
        if(user != null){
            userRepository.delete(user);
            return user;
        }
        return null;
    }

}
