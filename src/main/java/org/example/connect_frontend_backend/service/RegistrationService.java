package org.example.connect_frontend_backend.service;

import org.example.connect_frontend_backend.model.RegistrationRequest;
import org.example.connect_frontend_backend.model.appuser.AppUser;

public interface RegistrationService {
    public String register(RegistrationRequest request);

}
