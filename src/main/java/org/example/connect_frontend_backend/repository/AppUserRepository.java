package org.example.connect_frontend_backend.repository;


import org.example.connect_frontend_backend.model.appuser.AppUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly=true)
public interface AppUserRepository {
    Optional<AppUser> findByEmail(String email);

}
