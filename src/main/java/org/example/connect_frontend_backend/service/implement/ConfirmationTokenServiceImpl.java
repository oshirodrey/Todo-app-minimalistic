package org.example.connect_frontend_backend.service.implement;

import lombok.AllArgsConstructor;
import org.example.connect_frontend_backend.model.appuser.token.ConfirmationToken;
import org.example.connect_frontend_backend.repository.ConfirmationTokenRepository;
import org.example.connect_frontend_backend.service.ConfirmationTokenService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {


    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }
}
