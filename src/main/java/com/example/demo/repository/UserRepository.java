package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserRepository {
    private final NamedParameterJdbcTemplate db;
    private final UserRowMapper userMapper = new UserRowMapper();

    public UserRepository(NamedParameterJdbcTemplate db) {
        this.db = db;
    }

    public Optional<User> findById(String id) {
        log.info("findById: " + id);
        final String sql = "SELECT * FROM users WHERE id=:id";

        SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);

        return db.query(sql, params, userMapper).stream().findFirst();
    }

    public User save(User user) {
        final String sql = "INSERT INTO users(id, password) VALUES (:id, :password)";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", user.getUserId())
                .addValue("password", user.getPassword());

        db.update(sql, params);

        return user;
    }
}

class UserRowMapper implements RowMapper<User> {

    @Override
    public @Nullable User mapRow(ResultSet rs, int rowNum) throws SQLException {
        String id = rs.getString("id");
        String password = rs.getString("password");

        return new User(id, password);
    }

}
