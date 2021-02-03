package kim.junhong.expspringbatch.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScheduleTask {

    @Scheduled(fixedDelay = 10000)
    public void task1() {
        System.out.println("LocalDateTime.now() = " + LocalDateTime.now());
    }
}
