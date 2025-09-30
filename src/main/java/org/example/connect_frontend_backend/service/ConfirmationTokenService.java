package org.example.connect_frontend_backend.service;

import org.example.connect_frontend_backend.model.appuser.token.ConfirmationToken;

public interface ConfirmationTokenService {
    public void saveConfirmationToken(ConfirmationToken token);

}
