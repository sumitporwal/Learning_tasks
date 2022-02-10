package com.example.SpringBoot_MongoDB.Controller;

import com.example.SpringBoot_MongoDB.Model.User;
import com.example.SpringBoot_MongoDB.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/users/{userId}")
    public User getUsers(@PathVariable String userId){
        return userService.getUser(userId);
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping("/edit/{userId}")
    public String editUser(@PathVariable String userId, @RequestBody User user){
        return userService.editUser(userId,user);
    }

    @DeleteMapping("/delete/{userId}")
    public User deleteUser(@PathVariable String userId){
        return userService.deleteUser(userId);
    }
}
