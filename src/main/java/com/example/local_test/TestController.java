package com.example.local_test;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final SampleRepository sampleRepository;

    private final JdbcTemplate jdbcTemplate;

    private static final String query = """
            INSERT INTO sample (number)
            VALUES (?)
            """;

    @PostMapping("/samples")
    public boolean createSample() {
        for (int i = 0; i < 1000; i++) {
            init();
        }

        return true;
    }

    private void init() {
        jdbcTemplate.batchUpdate(query,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, ThreadLocalRandom.current().nextLong());
                    }

                    @Override
                    public int getBatchSize() {
                        return 1000;
                    }
                });
    }

    @GetMapping("/samples")
    public List<Sample> getSamples() {
        return sampleRepository.findAllByNumber(ThreadLocalRandom.current().nextLong());
    }
}
