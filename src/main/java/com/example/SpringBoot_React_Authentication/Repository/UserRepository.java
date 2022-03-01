package com.example.SpringBoot_React_Authentication.Repository;

import com.example.SpringBoot_React_Authentication.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
