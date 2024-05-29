package com.scm.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

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
        model.addAttribute("isLogin", false);

        return "register";
    }
}
