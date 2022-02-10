package com.example.demo.Service;

import com.example.demo.DAO.UserRepository;
import com.example.demo.Model.User;
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

    public String addUser(User user){
        userRepository.save(user);
        return "user " + user.getName() + " added";
    }

    public String editUser(long id,User user1){
        User user = userRepository.findOneById(id);
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

    public String deleteUser(long id){
        User user = userRepository.findOneById(id);
        String previousName = user.getName();
        if(user != null){
            userRepository.delete(user);
            return "user deleted with name " + previousName;
        }
        return "user not found";
    }

}
