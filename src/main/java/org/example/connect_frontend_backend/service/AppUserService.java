package org.example.connect_frontend_backend.service;

import org.example.connect_frontend_backend.model.appuser.AppUser;

public interface AppUserService {

    public String signUpUser(AppUser appUser) ;
    public int enableAppUser(String email);

}
