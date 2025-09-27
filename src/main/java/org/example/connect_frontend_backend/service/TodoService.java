package org.example.connect_frontend_backend.service;

import org.example.connect_frontend_backend.model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getAllTodos();

    Todo createTodo(Todo todo);

    void deleteTodo(Long id) throws Exception;




}
