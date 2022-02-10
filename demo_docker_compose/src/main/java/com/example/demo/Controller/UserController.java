package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/add")
    public String addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping("/edit/{id}")
    public String editUser(@PathVariable long id,@RequestBody User user){
        return userService.editUser(id,user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id){
        return userService.deleteUser(id);
    }
}
