package com.example.SpringBoot_Testing.Service;

import com.example.SpringBoot_Testing.Exception.NotFoundException;
import com.example.SpringBoot_Testing.Model.Department;
import com.example.SpringBoot_Testing.Model.Employee;
import com.example.SpringBoot_Testing.Repository.DepartmentRepository;
import com.example.SpringBoot_Testing.Repository.EmployeeRepository;
import com.example.SpringBoot_Testing.Views.EmployeeRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private DepartmentService departmentService;

    List<Employee> employee = new ArrayList<>();
    Department department1 = new Department(1L,"Engineering",employee);
    Department department2 = new Department(2L,"Finance",employee);
    Department department3 = new Department(3L,"HR",employee);

    Employee employee1 = new Employee(1L,"Sumit","sumit@gmail.com","9838883821",21,20000.00,null);
    Employee employee2 = new Employee(2L,"Tarun","tarun@gmail.com","9728883821",25,50000.00,null);
    Employee employee3 = new Employee(3L,"Kartik","kartik@gmail.com","9982832821",21,100000.00,null);

    @Test
    public void testAddEmployee(){
        EmployeeRequest record = EmployeeRequest.builder()
                .name("Sumit")
                .email("sumit@gmail.com")
                .phno("9838883821")
                .age(21)
                .salary(20000.00)
                .department(null)
                .build();
        when(employeeRepository.save(employee2)).thenReturn(employee2);
        Employee result = employeeService.addEmployee(record);
        assertThat(result.getName()).isEqualTo("Sumit");
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        List<Employee> employees = new ArrayList<>(Arrays.asList(employee1,employee2,employee3));
        when(employeeRepository.findAll()).thenReturn(employees);
        List<Employee> returnedEmployees = employeeService.getEmployees();
        assertThat(returnedEmployees.size()).isEqualTo(3);
    }

    @Test
    public void testGetEmployeeById_Found() {
        when(employeeRepository.getById(1L)).thenReturn(employee1);
        Employee result = employeeService.getEmployeeByID(1L);
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Sumit");
    }

    @Test
    public void testGetEmployeeById_NotFound() {
        when(employeeRepository.getById(4L)).thenReturn(null);
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> employeeService.getEmployeeByID(4L));
    }

    @Test
    public void testUpdateEmployee() {
        EmployeeRequest record = EmployeeRequest.builder()
                .name("Ashutosh")
                .email("ashutosh@gmail.com")
                .phno("9838883881")
                .age(32)
                .salary(200000.00)
                .build();
        Employee employee4 = new Employee(1L,"Ashutosh","ashutosh@gmail.com","9838883881",32,200000.00,null);
        when(employeeRepository.getById(1L)).thenReturn(employee1);
        when(employeeRepository.save(employee1)).thenReturn(employee1);

        Employee result = employeeService.updateEmployee(1L,record);
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Ashutosh");
    }

    @Test
    public void testUpdateEmployee_NotFound(){
        EmployeeRequest record = EmployeeRequest.builder()
                .name("Sumit")
                .email("sumit@gmail.com")
                .phno("9838883821")
                .age(21)
                .salary(20000.00)
                .department("Engineering")
                .build();
        when(employeeRepository.getById(4L)).thenReturn(null);
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> employeeService.updateEmployee(4L,record));
    }

    @Test
    public void testDeleteEmployee_Found(){
        when(employeeRepository.getById(2L)).thenReturn(employee2);

        Employee result = employeeService.deleteEmployee(2L);
        assertThat(result.getId()).isEqualTo(2L);
        assertThat(result.getName()).isEqualTo("Tarun");
    }

    @Test
    public void testDeleteDepartment_NotFound(){
        assertThatThrownBy(() -> {
            employeeService.deleteEmployee(5L).equals("Employee Not Found");
        }).isInstanceOf(NotFoundException.class)
                .hasMessage("Employee Not Found");
    }
}
