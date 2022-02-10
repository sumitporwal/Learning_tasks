package com.example.swaggerImplementation.DAO;

import com.example.swaggerImplementation.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneById(long id);

}
