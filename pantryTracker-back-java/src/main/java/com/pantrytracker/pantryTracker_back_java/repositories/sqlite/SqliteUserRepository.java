package com.pantrytracker.pantryTracker_back_java.repositories.sqlite;

import com.pantrytracker.pantryTracker_back_java.models.User;
import com.pantrytracker.pantryTracker_back_java.repositories.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

@Repository
public class SqliteUserRepository implements UserRepository {

    private final JdbcTemplate jdbc;

    public SqliteUserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = """
            SELECT id, user_name, email, password
            FROM users
            WHERE id = ?
        """;

        return jdbc.query(sql, rs -> {
            if (!rs.next()) return Optional.empty();
            return Optional.of(mapUser(rs));
        }, id);
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        String sql = """
            SELECT id, user_name, email, password
            FROM users
            WHERE user_name = ?
        """;

        return jdbc.query(sql, rs -> {
            if (!rs.next()) return Optional.empty();
            return Optional.of(mapUser(rs));
        }, userName);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = """
            SELECT id, user_name, email, password
            FROM users
            WHERE email = ?
        """;

        return jdbc.query(sql, rs -> {
            if (!rs.next()) return Optional.empty();
            return Optional.of(mapUser(rs));
        }, email);
    }

    @Override
    public boolean existsByUserName(String userName) {
        String sql = "SELECT COUNT(*) FROM users WHERE user_name = ?";
        Integer count = jdbc.queryForObject(sql, Integer.class, userName);
        return count != null && count > 0;
    }

    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = jdbc.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    @Override
    public String getPasswordHashByUserName(String userName) {
        String sql = "SELECT password FROM users WHERE user_name = ?";
        return jdbc.query(sql, rs -> {
            if (!rs.next()) return null;
            return rs.getString("password");
        }, userName);
    }

    @Override
    public String getPasswordHashByEmail(String email) {
        String sql = "SELECT password FROM users WHERE email = ?";
        return jdbc.query(sql, rs -> {
            if (!rs.next()) return null;
            return rs.getString("password");
        }, email);
    }

    @Override
    public String getUserNameByEmail(String email) {
        String sql = "SELECT user_name FROM users WHERE email = ?";
        return jdbc.query(sql, rs -> {
            if (!rs.next()) return null;
            return rs.getString("user_name");
        }, email);
    }

    @Override
    public String getEmailByUserName(String userName) {
        String sql = "SELECT email FROM users WHERE user_name = ?";
        return jdbc.query(sql, rs -> {
            if (!rs.next()) return null;
            return rs.getString("email");
        }, userName);
    }

    @Override
    public Long getUserIdByUserName(String userName) {
        String sql = "SELECT id FROM users WHERE user_name = ?";
        return jdbc.query(sql, rs -> {
            if (!rs.next()) return null;
            return rs.getLong("id");
        }, userName);
    }

    @Override
    public Long getUserIdByEmail(String email) {
        String sql = "SELECT id FROM users WHERE email = ?";
        return jdbc.query(sql, rs -> {
            if (!rs.next()) return null;
            return rs.getLong("id");
        }, email);
    }

    @Override
    public Long save(User user) {
        String sql = """
            INSERT INTO users (user_name, email, password)
            VALUES (?, ?, ?)
        """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(con -> {
            PreparedStatement ps =
                    con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private User mapUser(java.sql.ResultSet rs) throws java.sql.SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUserName(rs.getString("user_name"));
        user.setEmail(rs.getString("email"));
        // No establecer la contraseña para mayor seguridad
        // user.setPassword(rs.getString("password"));
        return user;
    }
}

