package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.TodoForm;
import com.example.demo.entity.TaskStatus;
import com.example.demo.entity.Todo;
import com.example.demo.exception.TodoNotFoundException;
import com.example.demo.repository.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TodoService {
    private TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> getCompleted() {
        List<Todo> todos = repository.findCompleted();
        return todos;
    }

    public List<Todo> getWorking() {
        List<Todo> todos = repository.findWorking();
        return todos;
    }

    public Todo findById(long id) {
        Todo todo = repository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
        return todo;
    }

    @Transactional
    public Todo create(TodoForm form) {
        Todo todo = new Todo();

        todo.setTitle(form.getTitle());
        todo.setDetail(form.getDetail());
        todo.setDeletedAt(form.getDeadline());
        todo.setPriority(form.getPriority());

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

        LocalDateTime compltedAt = todo.getStatus() == TaskStatus.COMPLETED ? LocalDateTime.now() : null;
        todo.setCompletedAt(compltedAt);

        repository.save(todo);

        return todo;
    }
}
