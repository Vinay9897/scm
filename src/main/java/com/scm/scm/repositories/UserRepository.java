package com.scm.scm.repositories;


import com.scm.scm.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    
    @Query(value = "select * from users where username = ?1" , nativeQuery = true)
    Optional<User> findByEmail(String username);
}
