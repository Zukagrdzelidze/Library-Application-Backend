package com.example.finalprojectreport.service.implementations;

import com.example.finalprojectreport.constants.CsvConstants;
import com.example.finalprojectreport.model.CsvReportRequest;
import com.example.finalprojectreport.service.ReportService;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {


    @Override
    public void generateReport(List<CsvReportRequest> list) {
        String fileName = CsvConstants.FILENAME;
        File file = new File(fileName);

        try (FileWriter writer = new FileWriter(file)) {
            List<CsvReportRequest> books = list;

            new StatefulBeanToCsvBuilder<CsvReportRequest>(writer)
                    .build()
                    .write(books);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating CSV file", e);
        }
    }
}
