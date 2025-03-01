package com.example.finalprojectreport.service;

import com.example.finalprojectreport.model.CsvReportRequest;

import java.util.List;

public interface ReportService {
    void generateReport(List<CsvReportRequest> list);
}
