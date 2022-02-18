package com.example.SpringBoot_Testing.Controller;

import com.example.SpringBoot_Testing.Model.Employee;
import com.example.SpringBoot_Testing.Service.EmployeeService;
import com.example.SpringBoot_Testing.Views.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/add")
    public Employee addEmployee(@RequestBody EmployeeRequest employeeRequest){
        return employeeService.addEmployee(employeeRequest);
    }

    @GetMapping
    public List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

    @PostMapping("/{id}")
    public Employee getEmployeeById(@PathVariable long id){
        return employeeService.getEmployeeByID(id);
    }

    @PostMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable long id,@RequestBody EmployeeRequest employeeRequest){
        return employeeService.updateEmployee(id,employeeRequest);
    }

    @PostMapping("/delete/{id}")
    public Employee deleteEmployee(@PathVariable long id){
        return employeeService.deleteEmployee(id);
    }
}
