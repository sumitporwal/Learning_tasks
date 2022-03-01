package com.example.SpringBoot_React_Authentication.Request_Response;

import com.example.SpringBoot_React_Authentication.Model.Role;
import com.example.SpringBoot_React_Authentication.Model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserResponse {

    private String name;
    private String email;
    private String username;
    private Date dob;
    private String password;
    private List<Role> roles;

    public UserResponse(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.dob = user.getDob();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }
}
