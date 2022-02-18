package com.example.SpringBoot_Testing.Repository;

import com.example.SpringBoot_Testing.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    public Department findOneByName(String name);
}
