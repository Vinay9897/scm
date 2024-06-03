package com.scm.scm.controller;

import com.scm.scm.entities.User;
import com.scm.scm.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.scm.scm.entities.UserForm;

@Controller
public class PageController {

    private UserService userService;

    public PageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/base")
    public String base(Model model) {
        model.addAttribute("title", "Hello World");
        model.addAttribute("links", "Hello World");
        return "base";
    }

    @GetMapping("/service")
    public String service(Model model) {
        model.addAttribute("title", "Hello World");
        model.addAttribute("links", "Hello World");
        return "service";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("isLogin", false);
        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("isLogin", false);
        return "contact";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("isLogin", false);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
    	UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }
    
    @PostMapping(value="/doregister")
    public String processingRegister(@ModelAttribute UserForm userForm ) {
        User user = User.builder()
                .name(userForm.getUserName())
                .password(userForm.getPassword())
                .emailId(userForm.getEmail())
                .about(userForm.getAbout())
                .phoneNo(userForm.getPhoneNumber())
                .profilePic("")
                .build();
    	String savedUser = userService.saveUser(user);
        System.out.println(savedUser);
    	return "redirect:/register";
    }
}

