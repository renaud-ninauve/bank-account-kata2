package fr.ninauve.kata.bankaccount;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;

@Configuration
public class ClockConfig {

    private static final String ZONE_ID_PARIS = "Europe/Paris";

    @Bean
    public Clock clock() {
        return Clock.system(ZoneId.of(ZONE_ID_PARIS));
    }
}
