package com.nldk.services.iservices;

import com.nldk.pojo.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface IUserServices extends UserDetailsService {
    UserDetails getUserByEmailAndPassword(String email, String password);
}
