package com.example.local_test.temp;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class SubwayStatsController {

    private final SubwayStatsBulkInsertService normalService;
    private final SubwayStatsBulkInsertOptimizedService optimizedService;

    @PostMapping("/bulk-insert")
    public void bulkInsert() {
        List<SubwayStats> testData = setUp();
        normalService.insertBulkData(testData);
    }

    @PostMapping("/bulk-insert/optimized")
    public void bulkInsertOptimized() {
        List<SubwayStats> testData = setUp();
        optimizedService.insertBulkData(testData);
    }

    /**
     * - 테스트 데이터 생성
     * - 10개 역 = 1Set
     * - 24시간(1440분)을 10 분 간격으로 144 개의 Set 데이터 생성
     * => 10 * 144 = 1440개의 데이터 생성
     */
    private List<SubwayStats> setUp() {
        List<SubwayStats> result = new ArrayList<>(1440);

        LocalDateTime startTime = LocalDate.now().atStartOfDay()
                .minusDays(1L)
                .truncatedTo(ChronoUnit.HOURS);

        for (int station = 1; station <= 10; station++) {
            for (int min = 0; min < 1440; min += 10) {
                LocalDateTime recordTime = startTime.plusMinutes(min);
                SubwayStats subwayStats = new SubwayStats("역-" + station, recordTime);
                result.add(subwayStats);
            }
        }

        return result;
    }
}
