package com.example.finalproject.service;

import com.example.finalproject.model.response.AdminReportResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ReportService {
    void createCsvFile();

    List<AdminReportResponse> adminReport();

    void generateReport() throws JsonProcessingException;
}
