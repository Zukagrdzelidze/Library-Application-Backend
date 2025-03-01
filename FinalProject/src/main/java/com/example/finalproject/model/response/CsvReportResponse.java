package com.example.finalproject.model.response;

import jakarta.persistence.Access;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CsvReportResponse {
    private String customerName;

    private String bookName;

    private String status;

}
