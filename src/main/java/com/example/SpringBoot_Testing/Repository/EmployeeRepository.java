package com.example.SpringBoot_Testing.Repository;

import com.example.SpringBoot_Testing.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
