package com.scm.scm.serviceimpl;

import com.scm.scm.entities.User;
import com.scm.scm.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService {

    Logger logger = Logger.getLogger(this.getClass().getName());

    private UserRepository userRepository;

    public SecurityCustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
    logger.info(" Logger in SecurityCustomUserDetail Service class=========================");

    System.out.println("user is Present " + user.isPresent());
        logger.info("email : " + user.get() );

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.get().getEmailId(),
                user.get().getPassword(),
                new ArrayList<>()
        );
    }
}
