package com.example.SpringBoot_Testing.Controller;

import com.example.SpringBoot_Testing.Model.Department;
import com.example.SpringBoot_Testing.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping("/add")
    public Department addDepartment(@RequestBody Department department){
        return departmentService.addDepartment(department);
    }

    @GetMapping
    public List<Department> getDepartments(){
        return departmentService.getDepartments();
    }

    @PostMapping("/update/{id}")
    public Department updateDepartment(@PathVariable long id,@RequestBody Department department){
        return departmentService.updateDepartment(id,department);
    }

    @PostMapping("/delete/{id}")
    public Department deleteDepartment(@PathVariable long id){
        return departmentService.deleteDepartment(id);
    }
}
