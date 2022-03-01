package com.example.SpringBoot_React_Authentication.Controller;

import com.example.SpringBoot_React_Authentication.Model.User;
import com.example.SpringBoot_React_Authentication.Request_Response.LoginRequest;
import com.example.SpringBoot_React_Authentication.Request_Response.LoginResponse;
import com.example.SpringBoot_React_Authentication.Request_Response.UserRequest;
import com.example.SpringBoot_React_Authentication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
@RestController
public class UserAuthController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public User registerUser(@RequestBody UserRequest userRequest){
        return userService.registerUser(userRequest);
    }

    @PostMapping("/signin")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }
}
