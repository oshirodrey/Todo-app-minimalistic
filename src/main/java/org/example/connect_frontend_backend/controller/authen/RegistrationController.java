package org.example.connect_frontend_backend.controller.authen;

import lombok.AllArgsConstructor;
import org.example.connect_frontend_backend.model.RegistrationRequest;
import org.example.connect_frontend_backend.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
@AllArgsConstructor
@ResponseStatus(HttpStatus.OK)
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
    //path = localhost:8080/api/registration/confirm?token=
    //type = get

}
