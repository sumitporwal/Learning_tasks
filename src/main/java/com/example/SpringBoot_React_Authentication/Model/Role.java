package com.example.SpringBoot_React_Authentication.Model;

import com.example.SpringBoot_React_Authentication.Enum.ERole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}
