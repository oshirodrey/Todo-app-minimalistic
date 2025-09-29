package org.example.connect_frontend_backend.service;

import lombok.AllArgsConstructor;
import org.example.connect_frontend_backend.model.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService{
    @Override
    public String register(RegistrationRequest request) {
        return "WORKS";
    }
}
