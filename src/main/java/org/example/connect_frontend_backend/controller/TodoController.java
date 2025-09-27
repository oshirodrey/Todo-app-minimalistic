package org.example.connect_frontend_backend.controller;

import org.example.connect_frontend_backend.model.ApiResponse;
import org.example.connect_frontend_backend.model.Todo;
import org.example.connect_frontend_backend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/api")
    public ApiResponse homeController() {
        ApiResponse res = new ApiResponse();
        res.setMessage("todo viewed successfully");
        res.setStatus(true);
        return res;
    }

    @GetMapping("/api/todos")
    public List<Todo> getAllTodos(){

        return todoService.getAllTodos();
    }

    @PostMapping("/api/todos")
    public Todo createTodo(@RequestBody Todo todo){
        return todoService.createTodo(todo);
    }

    @DeleteMapping("/api/todos/{id}")
    public ApiResponse deleteTodo(@PathVariable Long id ) throws Exception {
        todoService.deleteTodo(id);
        ApiResponse res = new ApiResponse();
        res.setMessage("todo deleted successfully");
        res.setStatus(true);
        return res;
    }





}
