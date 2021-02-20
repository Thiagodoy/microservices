package com.allofus.admin.service;

import com.allofus.admin.model.User;
import com.allofus.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;

@org.springframework.stereotype.Service
public class UserService extends Service<User, UserRepository> {
    public UserService(@Qualifier("userRepository") UserRepository userRepository) {
        super(userRepository);
    }
}
