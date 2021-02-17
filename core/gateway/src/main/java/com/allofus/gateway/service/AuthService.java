/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allofus.gateway.service;

import com.allofus.gateway.model.User;
import com.allofus.gateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class AuthService implements UserDetailsService {


    @Autowired
    private UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        return repository.findByEmail(string).orElseThrow(() -> new UsernameNotFoundException("Usuário não cadastrado!"));
    }

    @Transactional
    public void updateAttempts(String email) {
        repository.findByEmail(email).ifPresent(user->{
            User us = (User)user;
            us.setAttempts(us.getAttempts() + 1L);
            if(us.getAttempts().intValue() == 5){
                us.setIsEnable(false);
            }
            this.repository.save(user);
        });
    }

    @Transactional
    public void resetAttempts(String email) {
        repository.findByEmail(email).ifPresent(user->{
            User us = (User)user;
            us.setAttempts(0L);
            this.repository.save(user);
        });
    }
}
