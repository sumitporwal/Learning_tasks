package com.example.SpringBoot_Testing.Service;

import com.example.SpringBoot_Testing.Exception.NotFoundException;
import com.example.SpringBoot_Testing.Model.Department;
import com.example.SpringBoot_Testing.Model.Employee;
import com.example.SpringBoot_Testing.Repository.DepartmentRepository;
import com.example.SpringBoot_Testing.Repository.EmployeeRepository;
import com.example.SpringBoot_Testing.Views.EmployeeRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final DepartmentService departmentService;

    public Employee addEmployee(EmployeeRequest employeeRequest){
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setPhno(employeeRequest.getPhno());
        employee.setAge(employeeRequest.getAge());
        employee.setSalary(employeeRequest.getSalary());

        Department department = departmentRepository.findOneByName(employeeRequest.getDepartment());
        if(department==null){
            department = new Department();
            department.setName(employeeRequest.getDepartment());
            departmentService.addDepartment(department);
        }
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployeeByID(long id){
        Employee employee = employeeRepository.getById(id);
        if(employee!=null){
            return employeeRepository.getById(id);
        }
        else{
            throw new NotFoundException("Employee Not Found");
        }
    }

    public Employee updateEmployee(long id,EmployeeRequest employeeRequest){
        Employee employee1 = employeeRepository.getById(id);
        if(employee1!=null){
            employee1.setName(employeeRequest.getName());
            employee1.setEmail(employeeRequest.getEmail());
            employee1.setPhno(employeeRequest.getPhno());
            employee1.setAge(employeeRequest.getAge());
            employee1.setSalary(employeeRequest.getSalary());

            Department department = departmentRepository.findOneByName(employeeRequest.getDepartment());
            if(department==null){
                department = new Department();
                department.setName(employeeRequest.getDepartment());
                departmentService.addDepartment(department);
            }
            employee1.setDepartment(department);
            return employeeRepository.save(employee1);
        }
        else{
            throw new NotFoundException("Employee Not Found");
        }
    }

    public Employee deleteEmployee(long id){
        Employee employee = employeeRepository.getById(id);
        if(employee!=null){
            employeeRepository.delete(employee);
            return employee;
        }
        else{
            throw new NotFoundException("Employee Not Found");
        }
    }


}
