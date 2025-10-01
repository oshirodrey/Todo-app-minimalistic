package org.example.connect_frontend_backend.service;

import org.example.connect_frontend_backend.model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getAllTodos();

    Todo createTodo(Todo todo);

    void deleteTodo(Long id) throws Exception;

    // new
    List<Todo> getMyTodos(String email);

    Todo createForUser(String email, String title, String description);

    void deleteMyTodo(String email, Long id) throws Exception;



}
