package com.example.SpringBoot_Testing.Service;

import com.example.SpringBoot_Testing.Exception.NotFoundException;
import com.example.SpringBoot_Testing.Model.Department;
import com.example.SpringBoot_Testing.Repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public Department addDepartment(Department department){
        return departmentRepository.save(department);
    }

    public List<Department> getDepartments(){
        return departmentRepository.findAll();
    }

    public Department updateDepartment(long id,Department department){
        Department department1 = departmentRepository.getById(id);
        if(department1!=null){
            return departmentRepository.save(department);
        }
        else{
            throw new NotFoundException("Department Not Found");
        }
    }

    public Department deleteDepartment(long id){
        Department department = departmentRepository.getById(id);
        if(department!=null){
            departmentRepository.delete(department);
            return department;
        }
        else{
            throw new NotFoundException("Department Not Found");
        }
    }
}
