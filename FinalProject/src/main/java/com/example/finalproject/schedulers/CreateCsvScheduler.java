package com.example.finalproject.schedulers;

import com.example.finalproject.service.ReportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class CreateCsvScheduler {

    private final ReportService reportService;

    @Scheduled(cron = "0 0 8 * * *")
    public void refreshBooks() throws JsonProcessingException {
        reportService.generateReport();
        log.info("Created Report");
    }
}
