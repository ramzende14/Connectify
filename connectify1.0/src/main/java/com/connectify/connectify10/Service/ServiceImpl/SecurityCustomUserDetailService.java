package com.connectify.connectify10.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.connectify.connectify10.Repository.UserRepository;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        //apne user ko load karana he
        return userRepository.findByEmail(username)
        .orElseThrow(()-> new UsernameNotFoundException("user not found"));

    }
    
}
