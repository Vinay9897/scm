package com.scm.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MyController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "Hello World");
        model.addAttribute("links", "Hello World");
        return "home";
    }
    @GetMapping("/base")
    public String base(Model model) {
        model.addAttribute("title", "Hello World");
        model.addAttribute("links", "Hello World");
        return "base";
    } @GetMapping("/service")
    public String service(Model model) {
        model.addAttribute("title", "Hello World");
        model.addAttribute("links", "Hello World");
        return "service";
    }
}
