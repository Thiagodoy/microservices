/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allofus.gateway.service;

import com.allofus.gateway.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author thiag
 */
@Service
public class AuthService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        return User
                .builder()
                .email(string)
                .password("xxxx")
                .id(9999L)
                .build();
    }


}
