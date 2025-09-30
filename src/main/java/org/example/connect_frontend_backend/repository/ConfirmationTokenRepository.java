package org.example.connect_frontend_backend.repository;

import org.example.connect_frontend_backend.model.appuser.token.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository <ConfirmationToken,Long>{
    Optional<ConfirmationToken> findByToken(String confirmationToken);
}
