package com.pro06.service;

import com.pro06.entity.Role;
import com.pro06.entity.Status;
import com.pro06.entity.User;
import com.pro06.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User userInsert(User user) {
        user.setPw(passwordEncoder.encode(user.getPw()));
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        return userRepository.save(user);
    }

    @Override
    public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }

    @Override
    public User getId(String id) {
        return userRepository.getId(id);
    }
}
