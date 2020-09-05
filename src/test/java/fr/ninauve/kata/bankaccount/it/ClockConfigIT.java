package fr.ninauve.kata.bankaccount.it;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.*;

@Configuration
public class ClockConfigIT {

    public static final ZonedDateTime ZONED_DATE_TIME =
            ZonedDateTime.of(LocalDateTime.of(2020, Month.SEPTEMBER, 12, 11, 43), ZoneId.of("Europe/Paris"));

    @Bean
    Clock clock() {

        return Clock.fixed(ZONED_DATE_TIME.toInstant(), ZONED_DATE_TIME.getZone());
    }
}
