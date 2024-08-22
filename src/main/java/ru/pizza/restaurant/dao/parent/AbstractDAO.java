package ru.pizza.restaurant.dao.parent;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Data
public abstract class AbstractDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    protected AbstractDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
