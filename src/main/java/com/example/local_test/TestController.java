package com.example.local_test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final SampleRepository sampleRepository;

    @GetMapping("/test")
    public int test() throws InterruptedException {
        int random = ThreadLocalRandom.current().nextInt(1000);
        Thread.sleep(random);
        return random;
    }

    @PostMapping("/samples")
    public boolean createSample() {
        for (int i = 0; i < 100; i++) {
            Sample sample = new Sample();
            sampleRepository.save(sample);
        }
        return true;
    }

    @GetMapping("/samples")
    public long getSamples() {
        int nextInt = ThreadLocalRandom.current().nextInt();
        String keyWord = String.valueOf(nextInt);
        return sampleRepository.countAllByNameContaining(keyWord);
    }
}
