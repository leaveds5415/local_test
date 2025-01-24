package com.example.local_test.temp;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubwayStatsBulkInsertService {

    private final SubwayStatsRepository repository;

    @Transactional
    public long insertBulkData(List<SubwayStats> statsList) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        repository.saveAll(statsList);

        stopWatch.stop();
        return stopWatch.getTotalTimeMillis();
    }
}