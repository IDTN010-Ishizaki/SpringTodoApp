package com.example.demo.service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.TodoForm;
import com.example.demo.entity.AuthToken;
import com.example.demo.entity.TaskStatus;
import com.example.demo.entity.Todo;
import com.example.demo.exception.TodoNotFoundException;
import com.example.demo.repository.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TodoService {
    private final TodoRepository repository;

    public TodoService(TodoRepository repository) throws NoSuchAlgorithmException {
        this.repository = repository;
    }

    public List<Todo> getCompleted(AuthToken auth) {
        List<Todo> todos = repository.findCompletedByUserId(auth.getUserId());
        return todos;
    }

    public List<Todo> getWorking(AuthToken auth) {
        List<Todo> todos = repository.findWorkingByUserId(auth.getUserId());
        return todos;
    }

    public Todo findById(long id) {
        Todo todo = repository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
        return todo;
    }

    @Transactional
    public Todo create(AuthToken auth, TodoForm form) {
        Todo todo = Todo.builder()
                .userId(auth.getUserId())
                .title(form.getTitle())
                .detail(form.getDetail())
                .deadline(form.getDeadline())
                .priority(form.getPriority())
                .build();

        return repository.save(todo);
    }

    @Transactional
    public Todo deleteById(long id) {
        Todo todo = findById(id);

        todo.setDeletedAt(LocalDateTime.now());

        return repository.save(todo);
    }

    @Transactional
    public Todo changeStateById(long id, TaskStatus status) {
        Todo todo = findById(id);

        log.info(status.toString());

        todo.setStatus(status);

        LocalDateTime completedAt = todo.getStatus() == TaskStatus.COMPLETED ? LocalDateTime.now() : null;
        todo.setCompletedAt(completedAt);

        repository.save(todo);

        return todo;
    }
}
