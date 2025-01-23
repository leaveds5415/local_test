package com.example.local_test;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@Table(name = "sample")
@Entity
public class Sample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Sample() {
        int nextInt = ThreadLocalRandom.current().nextInt();
        this.name = String.valueOf(nextInt);
    }
}
