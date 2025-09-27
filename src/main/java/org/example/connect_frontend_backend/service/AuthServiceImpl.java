package org.example.connect_frontend_backend.service;

import org.example.connect_frontend_backend.model.User;
import org.example.connect_frontend_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean login(String username, String rawPassword) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Passwords should be hashed, compare securely
            return user.getPassword().equals(rawPassword);
        }
        return false;
    }
}
