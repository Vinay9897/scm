package com.scm.scm.controller;

import com.scm.scm.entities.User;
import com.scm.scm.helpers.Helper;

import com.scm.scm.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication){

        String username = Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.findUserByEmail(username);
        System.out.println(user.getName());
        System.out.println(user.getEmailId());
        model.addAttribute("loggedInUser", user);
    }

    Logger logger = LoggerFactory.getLogger(UserController.class);
    @RequestMapping(value="/dashboard" , method = RequestMethod.GET)
    public String userDashboard(){
        return "user/dashboard";
    }

    @RequestMapping(value="/profile" , method = RequestMethod.GET)
    public String userProfile(Model model, Authentication authentication){

        String username = Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.findUserByEmail(username);
        model.addAttribute("loggedInUser", user);
        logger.info(username);
        return "user/profile";
    }

}
