package org.example.connect_frontend_backend.service;

import org.example.connect_frontend_backend.model.Todo;
import org.example.connect_frontend_backend.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public void deleteTodo(Long id) throws Exception {

        Todo todo = todoRepository.findById(id).orElseThrow(()->new Exception("Todo not exist"));

        todoRepository.delete(todo);

    }
}
