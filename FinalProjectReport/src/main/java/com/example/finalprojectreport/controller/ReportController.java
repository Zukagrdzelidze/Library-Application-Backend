package com.example.finalprojectreport.controller;

import com.example.finalprojectreport.model.CsvReportRequest;
import com.example.finalprojectreport.service.ReportService;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

@RestController
@RequestMapping("report-internal")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    public void createReport(@RequestBody List<CsvReportRequest> list){
        reportService.generateReport(list);
    }

}
