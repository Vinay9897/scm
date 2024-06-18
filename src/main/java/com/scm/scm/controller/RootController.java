package com.scm.scm.controller;

import com.scm.scm.entities.User;
import com.scm.scm.helpers.Helper;
import com.scm.scm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class RootController {

    private UserService userService;

    @Autowired
    public RootController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication){
        if(authentication == null){
            return ;
        }
        String username = Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.findUserByEmail(username);
//        if(user == null){
//            model.addAttribute("loggedInUser", null);
//        }
//        else{

        System.out.println(user.getName());
        System.out.println(user.getEmailId());
        model.addAttribute("loggedInUser", user);
        }
    }
