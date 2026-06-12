package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.example.demo.entity.Todo.TodoBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class TodoRepository {
    private NamedParameterJdbcTemplate template;

    private RowMapper<Todo> todoMapper = new TodoRowMapper();

    public TodoRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public List<Todo> findWorkingByUserId(String userId) {
        final String sql = "SELECT * FROM todos WHERE user_id=:user_id AND NOT task_status = 'COMPLETED' AND deleted_at IS NULL";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("user_id", userId);
        return template.query(sql, params, todoMapper);
    }

    public List<Todo> findCompletedByUserId(String userId) {
        final String sql = "SELECT * FROM todos WHERE user_id=:user_id AND task_status = 'COMPLETED' AND deleted_at IS NULL";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("user_id", userId);
        return template.query(sql, params, todoMapper);
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
        final String sql = "INSERT INTO todos (user_id, title, detail, task_priority, deadline) VALUES (:user_id, :title, :detail, :task_priority, :deadline)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("user_id", todo.getUserId())
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

class TodoRowMapper implements RowMapper<Todo> {

    @Override
    public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
        TodoBuilder builder = Todo.builder();

        // identity
        builder.id(rs.getLong("id"))
                .userId(rs.getString("user_id"));

        // content
        builder.title(rs.getString("title"))
                .detail(rs.getString("detail"))
                .priority(rs.getInt("task_priority"))
                .status(TaskStatus.valueOf(rs.getString("task_status")));

        builder.deadline(rs.getObject("deadline", LocalDateTime.class))
                .createdAt(rs.getObject("deadline", LocalDateTime.class))
                .createdAt(rs.getObject("created_at", LocalDateTime.class))
                .completedAt(rs.getObject("completed_at", LocalDateTime.class))
                .deletedAt(rs.getObject("deleted_at", LocalDateTime.class));

        return builder.build();
    }
}