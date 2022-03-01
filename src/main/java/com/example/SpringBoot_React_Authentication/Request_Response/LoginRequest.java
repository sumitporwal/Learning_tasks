package com.example.SpringBoot_React_Authentication.Request_Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String username;
    private String password;
}
