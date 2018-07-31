package com.gy.cloud.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class CustomUserDetailsService implements UserDetailsService, Serializable {

    private static String FIND_BY_USERNAME = "SELECT username, password FROM user WHERE username = ?";

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = null;

        // 查找用户


        // 查找用户的权限

        if (user == null) {
            throw new UsernameNotFoundException("username " + username + " not found");
        }
        return null;
    }

}
