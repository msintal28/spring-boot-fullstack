package com.amigoscode.gratitude;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GratitudeRowMapper implements RowMapper<Gratitude> {
    @Override
    public Gratitude mapRow(ResultSet rs, int rowNum) throws SQLException {
        Gratitude gratitude = new Gratitude(
                rs.getLong("gratitude_id"),
                rs.getTimestamp("begin").toLocalDateTime(),
                rs.getTimestamp("ende").toLocalDateTime(),
                rs.getLong("customer_id")
        );
        return gratitude;
    }
}
