package com.nldk.services;

import com.nldk.pojo.User;
import com.nldk.repositories.UserRepository;
import com.nldk.services.iservices.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userDetailsService")
public class CustomUserDetailsService implements IUserServices {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
//    @Override
//    @Transactional
//    public void addUser(User user) {
//        user.setPasswordHash(bCryptPasswordEncoder.encode(user.getPasswordHash()));
//        userRepository.addUser(user);
//    }
//    
//    @Override
//    @Transactional(readOnly = true)
//    public User getUserByEmail(String email) {
//        return userRepository.getUsers(email).get(0);
//    }
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("Không tồn tại");
        }
        
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return  new org.springframework.security.core.userdetails.User( user.getEmail(), user.getPasswordHash(), authorities);
        
    }
}
