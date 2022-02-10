package com.example.SpringBoot_MongoDB;

import com.example.SpringBoot_MongoDB.DAO.UserRepository;
import com.example.SpringBoot_MongoDB.Model.User;
import com.example.SpringBoot_MongoDB.Services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService service;

    @MockBean
    private UserRepository repository;

    @Test
    public void getUsersTest() {
        when(repository.findAll()).thenReturn(Stream
                .of(new User("61f05e8a1ce2840b4a51166d", "Sumit", "sumit@gmail.com",  21), new User("61f05e8a1ce2840b4a51168d", "Kartik", "kartik@gmail.com", 16)).collect(Collectors.toList()));
        assertEquals(2, service.getUsers().size());
    }

    @Test
    public void getUserByUserIdTest() {
        String userId = "61f05e8a1ce2840b4a51166d";
        User user = new User("61f7c206cece912d675a52ee", "Sumit", "sumit@gmail.com",  21);
        when(repository.findOneByUserId(userId))
                .thenReturn(user);
        assertEquals(user, service.getUser(userId));
    }

    @Test
    public void saveUserTest() {
        User user = new User("61f05e8a1ce2840b4a51966d", "Tarun", "tarun@gmail.com",  25);
        when(repository.save(user)).thenReturn(user);
        assertEquals(user, service.addUser(user));
    }

}
