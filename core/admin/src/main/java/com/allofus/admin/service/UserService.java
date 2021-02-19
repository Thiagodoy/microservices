package com.allofus.admin.service;

import com.allofus.admin.model.User;
import com.allofus.admin.repository.UserRepository;

@org.springframework.stereotype.Service
public class UserService extends Service<User, UserRepository>{
    public UserService(UserRepository repositoy) {
        super(repositoy);
    }
}
