package com.example.local_test;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Long> {
    long countAllByNameContaining(String name);
}
