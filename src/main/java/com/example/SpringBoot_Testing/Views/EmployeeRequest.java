package com.example.SpringBoot_Testing.Views;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmployeeRequest {

    private String name;
    private String email;
    private String phno;
    private int age;
    private double salary;
    private String department;
}
