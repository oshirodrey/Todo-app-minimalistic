package org.example.connect_frontend_backend.repository;

import org.example.connect_frontend_backend.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {
}
