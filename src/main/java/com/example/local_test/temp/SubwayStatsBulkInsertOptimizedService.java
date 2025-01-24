package com.example.local_test.temp;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;


@RequiredArgsConstructor
@Service
public class SubwayStatsBulkInsertOptimizedService {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public long insertBulkData(List<SubwayStats> statsList) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        String sql = """
                INSERT INTO subway_stats (station_name, boarding_count, exiting_count, time) 
                VALUES (?, ?, ?, ?)
                """;

        jdbcTemplate.batchUpdate(sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        SubwayStats stats = statsList.get(i);
                        ps.setString(1, stats.getStationName());
                        ps.setInt(2, stats.getBoardingCount());
                        ps.setInt(3, stats.getExitingCount());
                        ps.setTimestamp(4, Timestamp.valueOf(stats.getTime()));
                    }

                    @Override
                    public int getBatchSize() {
                        return statsList.size();
                    }
                });

        stopWatch.stop();
        return stopWatch.getTotalTimeMillis();
    }
}