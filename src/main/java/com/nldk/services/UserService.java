package com.nldk.services;

import com.nldk.pojo.User;
import com.nldk.repositories.irepositories.IUserRepositories;
import com.nldk.services.iservices.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserServices {
    
    @Autowired
    private IUserRepositories userRepositories;
    

    @Override
    public void createUser(User user) {
        User newUser = new User();
        
        newUser.setEmail(user.getEmail());
        newUser.setPasswordHash(user.getPasswordHash());
        newUser.setPhone(user.getPhone());
        newUser.setRole(user.getRole());
        newUser.setFullName(user.getFullName());
        
        userRepositories.createUser(newUser);
    }
}
