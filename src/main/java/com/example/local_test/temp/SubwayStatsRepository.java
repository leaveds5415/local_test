package com.example.local_test.temp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubwayStatsRepository extends JpaRepository<SubwayStats, Long> {
}