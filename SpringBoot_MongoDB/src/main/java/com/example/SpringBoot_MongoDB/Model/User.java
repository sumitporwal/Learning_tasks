package com.example.SpringBoot_MongoDB.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@AllArgsConstructor
public class User {
    @Id
    private String userId;
    private String name;
    private String email;
    private int age;
}
