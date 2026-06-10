package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TaskStatus;
import com.example.demo.entity.Todo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class TodoRepository {
    private NamedParameterJdbcTemplate template;

    private RowMapper<Todo> todoMapper = (rs, idx) -> {
        Long id = rs.getLong("id");
        String title = rs.getString("title");
        String detail = rs.getString("detail");
        int priority = rs.getInt("task_priority");
        TaskStatus status = TaskStatus.valueOf(rs.getString("task_status"));
        LocalDateTime deadline = rs.getObject("deadline", LocalDateTime.class);
        LocalDateTime createdAt = rs.getObject("created_at", LocalDateTime.class);
        LocalDateTime completedAt = rs.getObject("completed_at", LocalDateTime.class);
        LocalDateTime deletedAt = rs.getObject("deleted_at", LocalDateTime.class);
        Todo todo = new Todo(id, title, detail, priority, status, deadline, createdAt, completedAt, deletedAt);
        return todo;
    };

    public TodoRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public List<Todo> findWorking() {
        final String sql = "SELECT * FROM todos WHERE NOT task_status = 'COMPLETED' AND deleted_at IS NULL";
        return template.query(sql, todoMapper);
    }

    public List<Todo> findCompleted() {
        final String sql = "SELECT * FROM todos WHERE task_status = 'COMPLETED' AND deleted_at IS NULL";
        return template.query(sql, todoMapper);
    }

    public Optional<Todo> findById(long id) {
        final String sql = "SELECT * FROM todos WHERE id=:id";
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        return template.query(sql, params, todoMapper).stream().findFirst();
    }

    public Todo save(Todo todo) {
        if (todo.getId() == null) {
            return create(todo);
        } else {
            return update(todo);
        }
    }

    private Todo create(Todo todo) {
        final String sql = "INSERT INTO todos (title, detail, task_priority, deadline) VALUES (:title, :detail, :task_priority, :deadline)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("title", todo.getTitle())
                .addValue("detail", todo.getDetail())
                .addValue("task_priority", todo.getPriority())
                .addValue("deadline", todo.getDeadline());

        template.update(sql, params, keyHolder, new String[] { "id" });

        Number generatedId = keyHolder.getKey();

        if (generatedId != null) {
            todo.setId(generatedId.longValue());
        }

        return todo;
    }

    private Todo update(Todo todo) {
        final String sql = """
                UPDATE todos
                    SET title=:title,
                        detail=:detail,
                        task_priority=:task_priority,
                        task_status=:task_status,
                        deadline=:deadline,
                        completed_at=:completed_at,
                        deleted_at=:deleted_at
                    WHERE id=:id;
                """;

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("title", todo.getTitle())
                .addValue("detail", todo.getDetail())
                .addValue("task_priority", todo.getPriority())
                .addValue("task_status", todo.getStatus().toString())
                .addValue("deadline", todo.getDeadline())
                .addValue("completed_at", todo.getCompletedAt())
                .addValue("deleted_at", todo.getDeletedAt())
                .addValue("id", todo.getId());

        template.update(sql, params);

        return todo;
    }
}
