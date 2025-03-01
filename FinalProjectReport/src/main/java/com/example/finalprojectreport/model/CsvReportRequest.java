package com.example.finalprojectreport.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CsvReportRequest {
    private String customerName;

    private String bookName;

    private String status;

}
