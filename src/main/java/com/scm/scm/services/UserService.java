package com.scm.scm.services;

import com.scm.scm.entities.User;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findUserByEmail(String email);
    String saveUser(User user) ;
    String updateUser(User user);
    void deleteUser(Long id);
    List<User> findAllUsers();
//    boolean isUserExists(String email);
    boolean isUserExistsByEmail(String email);
    Optional<User> findUserById(Long id);

}
