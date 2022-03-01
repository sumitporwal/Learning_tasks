package com.example.SpringBoot_React_Authentication.Request_Response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserRequest {

    private String name;
    private String email;
    private String username;
    private Date dob;
    private String password;
    private String[] roles;
}
