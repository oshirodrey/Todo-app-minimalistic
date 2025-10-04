package org.example.connect_frontend_backend.repository;


import org.example.connect_frontend_backend.model.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly=true)
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByEmail(String email);
    // Optional here wont return null if the email is not found
    @Transactional
    // Transactional annotation is needed to make sure that the database is updated on both sides.
    //Imagine you're transferring money between bank accounts:
    //Subtract $100 from Account A
    //Add $100 to Account B
    //If step 1 succeeds but step 2 fails, you've lost $100!
    // @Transactional prevents this by ensuring both operations complete or neither does.
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);


}
