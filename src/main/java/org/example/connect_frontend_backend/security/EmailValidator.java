package org.example.connect_frontend_backend.security;

import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String>{

    @Override
    public boolean test(String s) {
        // TODO: Regex to validate email
        return true;
    }
}
