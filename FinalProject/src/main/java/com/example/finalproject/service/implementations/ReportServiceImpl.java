package com.example.finalproject.service.implementations;

import com.example.finalproject.clients.ExternalApi;
import com.example.finalproject.constants.ReportApiConstants;
import com.example.finalproject.constants.OpenLibraryConstants;
import com.example.finalproject.constants.Status;
import com.example.finalproject.domain.Book;
import com.example.finalproject.domain.Customer;
import com.example.finalproject.domain.LoanBook;
import com.example.finalproject.exception.BookIdNotValidException;
import com.example.finalproject.exception.CustomerNotExistsException;
import com.example.finalproject.model.response.AdminReportResponse;
import com.example.finalproject.model.response.CsvReportResponse;
import com.example.finalproject.repository.BookRepository;
import com.example.finalproject.repository.CustomerRepository;
import com.example.finalproject.repository.LoanBookRepository;
import com.example.finalproject.service.ReportService;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final BookRepository bookRepository;
    private final LoanBookRepository loanBookRepository;
    private final CustomerRepository customerRepository;
    private final ExternalApi externalApi;

    /**
        Creates CSV File with report of Loaned Books and who loaned.
        This Function is asynchronous
     */
    @Override
    @Async
    public void createCsvFile() {
         CompletableFuture.runAsync(()-> {
            String fileName = OpenLibraryConstants.CSV_FILE_NAME;
            File file = new File(fileName);

            try (FileWriter writer = new FileWriter(file)) {
                List<CsvReportResponse> books = loanBookRepository.findLoanBookByStatus(Status.LOANED)
                        .stream().map(this::convertToCSVReportResponse).toList();

                new StatefulBeanToCsvBuilder<CsvReportResponse>(writer)
                        .build()
                        .write(books);

            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating CSV file", e);
            }
         });

    }


    /**
        Creates adminReport of how many times every book was loaned
     */
    @Override
    public List<AdminReportResponse> adminReport() {
        List<AdminReportResponse> adminReportResponses = new ArrayList<>();
        List<Book> list = bookRepository.findAll();
        for (Book book : list) {
            List<LoanBook> loanBookList = loanBookRepository.findLoanBookByBookId(book.getId());
            adminReportResponses.add(new AdminReportResponse(book.getTitle(), loanBookList.size()));
        }
        return adminReportResponses;
    }

    /**
        Creates CSV File with report of Loaned Books and who loaned.
        It calls localhost's 8082 port and we create report from 8082
        This Function is asynchronous
    */
    @Override
    @Async
    public void generateReport()  {
        CompletableFuture.runAsync(()-> {
            List<CsvReportResponse> books = loanBookRepository.findLoanBookByStatus(Status.LOANED)
                    .stream().map(this::convertToCSVReportResponse).toList();
            String url = ReportApiConstants.URL;
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<List<CsvReportResponse>> requestEntity = new HttpEntity<>(books, headers);
            try {
                ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
            } catch (RestClientException e) {

                throw new RuntimeException("Error while sending report", e);
            }
        });
    }



    private CsvReportResponse convertToCSVReportResponse(LoanBook loanBook){
        Book book = bookRepository.findById(loanBook.getBookId()).orElseThrow(BookIdNotValidException::new);
        Customer customer = customerRepository.findById(loanBook.getCustomerId()).orElseThrow(CustomerNotExistsException::new);
        return CsvReportResponse.builder().bookName(book.getTitle()).customerName(customer.getFirstName() + " " + customer.getLastName())
                .status(loanBook.getStatus()).build();

    }
}
