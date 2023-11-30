package com.pro06.service;

import com.pro06.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService {
    public User userInsert(User user);
    public PasswordEncoder passwordEncoder();
    public User getId(String id);
}
