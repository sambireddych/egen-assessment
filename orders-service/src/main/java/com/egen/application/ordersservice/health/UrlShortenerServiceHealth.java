package com.egen.application.ordersservice.health;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.net.Socket;

@Component
@Slf4j
public class UrlShortenerServiceHealth implements HealthIndicator {

    private static final String URL
            = "localhost:8081/api/v1/";

    @Override
    public Health health() {
        // check if url shortener service url is reachable
        try (Socket socket =
                     new Socket(new java.net.URL(URL).getHost(),80)) {
        } catch (Exception e) {
            log.warn("Failed to connect to: {}",URL);
            return Health.down()
                    .withDetail("error", e.getMessage())
                    .build();
        }
        return Health.up().build();
    }

}
