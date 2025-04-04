package com.nldk.controllers;


import com.nldk.pojo.User;
import com.nldk.services.CustomUserDetailsService;
import com.nldk.services.iservices.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("api")
public class AuthController {
    @Autowired
    private IUserServices userService;
    
    @GetMapping("/auth")
    public String Auth() {

        return "auth";
    }
}
