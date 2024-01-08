package com.amigoscode.gratitude;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jdbcgratitude")
public class GratitudeJDBCDataAccessService implements GratitudeDao {
    private final JdbcTemplate jdbcTemplate;
    private final GratitudeRowMapper gratitudeRowMapper;

    public GratitudeJDBCDataAccessService(JdbcTemplate jdbcTemplate, GratitudeRowMapper gratitudeRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.gratitudeRowMapper = gratitudeRowMapper;
    }

    @Override
    public List<Gratitude> selectAllGratitudes() {
        String sql = """
                SELECT *
                FROM gratitude
                """;
        return jdbcTemplate.query(sql, gratitudeRowMapper);
    }

    @Override
    public List<Gratitude> selectAllGratitudesByCustomerId(Integer customerId) {
        String sql = """
                SELECT *
                FROM gratitude
                WHERE customer_id = ?
                """;
        return jdbcTemplate.query(sql, gratitudeRowMapper, customerId);
    }

    @Override
    public void insertGratitude(Gratitude gratitude) {
        var sql = """
                INSERT INTO gratitude(begin, ende, customer_id)
                VALUES (?,?,?)
                """;
        jdbcTemplate.update(
                sql,
                gratitude.begin(),
                gratitude.ende(),
                gratitude.customerId()
        );
    }
}
