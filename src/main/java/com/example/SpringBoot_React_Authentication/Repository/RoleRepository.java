package com.example.SpringBoot_React_Authentication.Repository;

import com.example.SpringBoot_React_Authentication.Enum.ERole;
import com.example.SpringBoot_React_Authentication.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(ERole name);
}
