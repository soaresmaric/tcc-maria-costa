package com.tcc.coldstarts.tcc.service;

import org.springframework.stereotype.Service;

@Service
public class HeavyLoadService {

    public String performHeavyLoad() {
        try {
            // Simulating heavy load by sleeping for 10 seconds
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Heavy load completed!";
    }
}
