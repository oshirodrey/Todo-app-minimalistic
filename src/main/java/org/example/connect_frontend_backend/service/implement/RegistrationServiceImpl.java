package org.example.connect_frontend_backend.service.implement;

import lombok.AllArgsConstructor;
import org.example.connect_frontend_backend.model.RegistrationRequest;
import org.example.connect_frontend_backend.model.appuser.AppUser;
import org.example.connect_frontend_backend.model.appuser.AppUserRole;
import org.example.connect_frontend_backend.model.security.EmailValidator;
import org.example.connect_frontend_backend.service.RegistrationService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final AppUserServiceImpl appUserService;
    private final EmailValidator emailValidator;

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

}
