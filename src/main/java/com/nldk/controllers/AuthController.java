package com.nldk.controllers;


import com.nldk.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("api")
public class AuthController {
    @Autowired
    private CustomUserDetailsService userService;
    
    @GetMapping("/auth")
    public String Auth() {
//        System.out.println("Đã vào Register() controller!");
//        User user = new User();
//        
//        user.setEmail("nldangkhoa0712@gmail.com");
//        user.setPasswordHash("a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3");
//        user.setPhone("123456789");
//        user.setRole("admin");
//        user.setFullName("nldk");
        
//        userService.createUser(user);
        return "auth";
    }
}
