package com.example.seerbit.utils;

import com.example.seerbit.service.serviceimpl.TransactionServiceImplementation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Configuration
@EnableScheduling //helps enable scheduling
@Component
@Slf4j
public class ScheduleJob{
    @Scheduled(cron="*/1 * * * * *")
    public void scheduler() {
        if (isTimeDifferenceGreaterThan30Seconds()){
            TransactionServiceImplementation.keepStartTime.put("startTime", Instant.now());
            TransactionServiceImplementation.keepCurrentTime.put("currentTime", Instant.now());
        }
    }
    public boolean isTimeDifferenceGreaterThan30Seconds() {
      Instant start = TransactionServiceImplementation.keepStartTime.get("startTime");
      Instant current = TransactionServiceImplementation.keepCurrentTime.get("currentTime");
      log.info("startTime: "+start);
      log.info("current: "+current);
      log.info("checker:  "+ ChronoUnit.SECONDS.between(start, current));
        if (ChronoUnit.SECONDS.between(start, current) > 30) {
            return true;
        }
        return false;
    }
}
