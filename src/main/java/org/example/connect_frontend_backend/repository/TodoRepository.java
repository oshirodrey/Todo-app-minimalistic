package org.example.connect_frontend_backend.repository;

import org.example.connect_frontend_backend.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> {
    List<Todo> findByAppUserEmail(String email);
    boolean existsByIdAndAppUserEmail(Long id, String email);
}

