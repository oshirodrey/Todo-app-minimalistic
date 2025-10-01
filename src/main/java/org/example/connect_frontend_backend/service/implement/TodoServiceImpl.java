package org.example.connect_frontend_backend.service.implement;

import org.example.connect_frontend_backend.model.Todo;
import org.example.connect_frontend_backend.model.appuser.AppUser;
import org.example.connect_frontend_backend.repository.AppUserRepository;
import org.example.connect_frontend_backend.repository.TodoRepository;
import org.example.connect_frontend_backend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private AppUserServiceImpl appUserService;
    @Autowired
    private AppUserRepository appUserRepository;

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

    @Override
    public List<Todo> getMyTodos(String email) {
        return todoRepository.findByAppUserEmail(email);
    }

    @Override
    public Todo createForUser(String email, String title, String description) {
        AppUser user = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Todo t = new Todo();
        t.setTitle(title);
        t.setDescription(description);
        t.setAppUser(user);
        return todoRepository.save(t);
    }

    @Override
    public void deleteMyTodo(String email, Long id) throws Exception {
        if (!todoRepository.existsByIdAndAppUserEmail(id, email)) {
            throw new Exception("Not found or not your todo");
        }
        todoRepository.deleteById(id);
    }


}
