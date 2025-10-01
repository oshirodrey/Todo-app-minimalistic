package org.example.connect_frontend_backend.service.implement;

import lombok.AllArgsConstructor;
import org.example.connect_frontend_backend.model.RegistrationRequest;
import org.example.connect_frontend_backend.model.appuser.AppUser;
import org.example.connect_frontend_backend.model.appuser.AppUserRole;
import org.example.connect_frontend_backend.model.appuser.token.ConfirmationToken;
import org.example.connect_frontend_backend.model.security.EmailValidator;
import org.example.connect_frontend_backend.service.RegistrationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final AppUserServiceImpl appUserService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenServiceImpl confirmationTokenService;

    @Override
    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail){
            throw new IllegalArgumentException("Invalid email");
        }
        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getPassword(),
                        request.getEmail(),
                        AppUserRole.USER
                )
        ) ;
    }



    @Override
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationToken.getAppUser().getEmail());
        return "confirmed";
    }

}
