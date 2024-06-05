package com.scm.scm.controller;

import com.scm.scm.entities.User;
import com.scm.scm.entities.UserForm;
import com.scm.scm.helpers.Message;
import com.scm.scm.helpers.MessageType;
import com.scm.scm.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/signin")
    public String login(Model model) {
        model.addAttribute("isLogin", false);
        return "signin";
    }

    @GetMapping("/register")
    public String register(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @PostMapping(value = "/doregister")
    public String processingRegister(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult, HttpSession session) {
       if(bindingResult.hasErrors()){
           return "register";
       }

        User user = new User();
        user.setName(userForm.getUserName());
        user.setPassword(userForm.getPassword());
        user.setEmailId(userForm.getEmail());
        user.setAbout(userForm.getAbout());
        user.setPhoneNo(userForm.getPhoneNumber());
        user.setProfilePic("");
        String savedUser = userService.saveUser(user);
        System.out.println(savedUser);

        Message  message = Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message", message);
        return "redirect:/register";
    }
}

