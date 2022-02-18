package com.example.SpringBoot_Testing.Service;

import com.example.SpringBoot_Testing.Exception.NotFoundException;
import com.example.SpringBoot_Testing.Model.Department;
import com.example.SpringBoot_Testing.Repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    Department department1 = new Department(1L,"Engineering",null);
    Department department2 = new Department(2L,"Finance",null);
    Department department3 = new Department(3L,"HR",null);

    List<Department> departments = new ArrayList<>(Arrays.asList(department1,department2,department3));

    @Test
    public void getAllDepartments(){
        Mockito.when(departmentRepository.findAll()).thenReturn(departments);
        List<Department> result = departmentService.getDepartments();
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    public void testAddDepartment(){
        Mockito.when(departmentRepository.save(department1)).thenReturn(department1);
        Department result = departmentService.addDepartment(department1);
        assertThat(result.getName()).isEqualTo("Engineering");
    }

    @Test
    public void testUpdateDepartment() {
        Department department4 = new Department(1L,"Marketing",null);
        Mockito.when(departmentRepository.getById(1L)).thenReturn(department1);
        Mockito.when(departmentRepository.save(department4)).thenReturn(department4);

        Department result = departmentService.updateDepartment(1L,department4);
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Marketing");
    }

    @Test
    public void testUpdateDepartment_NotFound(){
        Department department4 = new Department(4L,"Marketing",null);
        assertThatThrownBy(() -> {
            departmentService.updateDepartment(4L,department4).equals("Department Not Found");
        }).isInstanceOf(NotFoundException.class)
                .hasMessage("Department Not Found");
    }

    @Test
    public void testDeleteDepartment_Found(){
        Mockito.when(departmentRepository.getById(2L)).thenReturn(department2);

        Department result = departmentService.deleteDepartment(2L);
        assertThat(result.getId()).isEqualTo(2L);
        assertThat(result.getName()).isEqualTo("Finance");
    }

    @Test
    public void testDeleteDepartment_NotFound(){
        assertThatThrownBy(() -> {
            departmentService.deleteDepartment(5L).equals("Department Not Found");
        }).isInstanceOf(NotFoundException.class)
                .hasMessage("Department Not Found");
    }

}
