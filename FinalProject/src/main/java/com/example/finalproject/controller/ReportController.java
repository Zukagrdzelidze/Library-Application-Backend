package com.example.finalproject.controller;

import com.example.finalproject.constants.OpenLibraryConstants;
import com.example.finalproject.model.response.AdminReportResponse;
import com.example.finalproject.model.response.ReportResponse;
import com.example.finalproject.service.ReportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.CompletableFuture;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;


    /**
        This function creates CSV File. It calls reportService where logic is implemented
     */
    @PostMapping("/csvReport")
    public ResponseEntity<ReportResponse> createCsv()  {
        reportService.createCsvFile();
        return new ResponseEntity<>(new ReportResponse(OpenLibraryConstants.CSV_FILE_NAME), HttpStatus.CREATED);
    }

    /**
        This function creates admin report. It calls reportService where logic is implemented
     */
    @GetMapping("/adminReport")
    public ResponseEntity<List<AdminReportResponse>> adminReport() {
        return new ResponseEntity<>(reportService.adminReport(), HttpStatus.OK);
    }


    /**
        Creates Report From 8082 Port
     */
    @PostMapping("/externalReport")
    public ResponseEntity<ReportResponse> generateReport() throws JsonProcessingException {
         reportService.generateReport();
        return new ResponseEntity<>(new ReportResponse(OpenLibraryConstants.CSV_FILE_NAME), HttpStatus.CREATED);
    }
}
