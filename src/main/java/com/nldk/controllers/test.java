package com.nldk.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class test {
    
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "Hello Khoa");
        
        return "hello";
    }
}
