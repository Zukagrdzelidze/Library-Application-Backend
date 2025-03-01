package com.example.finalproject.schedulers;

import com.example.finalproject.service.BookRefreshService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class RefreshBookScheduler {
    private final BookRefreshService bookRefreshService;

    @Scheduled(cron = "0 0 8 * * *")
    public void refreshBooks() {
        bookRefreshService.refreshAllBooks();
        log.info("Refreshed");
    }
}
