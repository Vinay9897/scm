package com.scm.scm.serviceimpl;

import com.scm.scm.entities.User;
import com.scm.scm.repositories.UserRepository;
import com.scm.scm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        return user;
    }

    @Override
    public String saveUser(User user) {
           
         User user1  = userRepository.findByEmail(user.getEmailId()).orElse(null);
         
        if(user1 == null)
        {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoleList(List.of("ROLE_USER"));
        	User user2 = userRepository.save(user);
            System.out.println(user2.toString());
        	return " user added";
        }
        else 
        	return "user already exist";
    }

    @Override
    public String updateUser(User user) {
       User existedUser = userRepository.findById(user.getUserId()).orElse(null);
       User updatedUser = new User();
       if(!existedUser.equals(null)) {
    	   updatedUser.setAbout(user.getAbout());
    	   updatedUser.setEmailId(user.getEmailId());
    	   updatedUser.setEmailVerified(user.getEmailVerified());
    	   updatedUser.setEnabled(user.getEnabled());
    	   updatedUser.setGender(user.getGender());
    	   updatedUser.setName(user.getName());
    	   updatedUser.setPassword(user.getPassword());
    	   updatedUser.setPhoneVerified(user.getPhoneVerified());
    	   updatedUser.setProfilePic(user.getProfilePic());
    	   updatedUser.setProvider(user.getProvider());
    	   updatedUser.setProviderUserId(user.getProviderUserId());
    	   userRepository.save(updatedUser);   
    	   return "user updated";
       }

    	   return "user doesn't exist";
    }

    @Override
    public void deleteUser(Long id) {
    	User user = userRepository.findById(id).orElseThrow(() ->
        {
//            new ResourceException("User with id" + id + " not found");
            return null;
        }
        );

            userRepository.delete(user);


    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

//    @Override
//    public boolean isUserExists(String email) {
//       User user = userRepository.findBy(email);
//       return !user.equals(null) ? true : false;
//    }

    @Override
    public boolean isUserExistsByEmail(String email) {
    	User user = userRepository.findByEmail(email).orElse(null);
        return !user.equals(null);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
}
