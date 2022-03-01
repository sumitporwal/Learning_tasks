package com.example.SpringBoot_React_Authentication.Service;

import com.example.SpringBoot_React_Authentication.Config.JwtUtils;
import com.example.SpringBoot_React_Authentication.Enum.ERole;
import com.example.SpringBoot_React_Authentication.Exception.ValidationException;
import com.example.SpringBoot_React_Authentication.Model.Role;
import com.example.SpringBoot_React_Authentication.Model.User;
import com.example.SpringBoot_React_Authentication.Repository.RoleRepository;
import com.example.SpringBoot_React_Authentication.Repository.UserRepository;
import com.example.SpringBoot_React_Authentication.Request_Response.LoginRequest;
import com.example.SpringBoot_React_Authentication.Request_Response.LoginResponse;
import com.example.SpringBoot_React_Authentication.Request_Response.UserRequest;
import com.example.SpringBoot_React_Authentication.Request_Response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;


    public User registerUser(UserRequest userRequest){
        User user = userRepository.findByUsername(userRequest.getUsername());
        if(user!=null){
            throw new ValidationException("User already Present with given Username");
        }
        User newUser = new User();
        newUser.setName(userRequest.getName());
        newUser.setEmail(userRequest.getEmail());
        newUser.setUsername(userRequest.getUsername());
        newUser.setDob(userRequest.getDob());
        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        List<Role> roleList = new ArrayList<>();
        for(String roleName: userRequest.getRoles()){
            Role role = roleRepository.findByName(ERole.valueOf(roleName));
            if(role!=null){
                roleList.add(role);
            }
        }
        newUser.setRoles(roleList);
        userRepository.save(newUser);
        return newUser;
    }

    public LoginResponse login(LoginRequest loginRequest){
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if(user==null){
            throw new ValidationException("User not Found with current Username");
        }
        if(user.getUsername().equals(loginRequest.getUsername()) && passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            return new LoginResponse(jwt,"Bearer",
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles);
        }
        else{
            throw new ValidationException("invalid username or password");
        }
    }

    public List<UserResponse> getAllUsers(){
        return userRepository.findAll().stream().map(UserResponse::new).collect(Collectors.toList());
    }

    public UserResponse getUser(Long id){
        return new UserResponse(userRepository.getById(id));
    }
}
