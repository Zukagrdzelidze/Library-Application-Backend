package com.example.finalproject.repository;

import com.example.finalproject.domain.LoanBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LoanBookRepository extends JpaRepository<LoanBook, Long> {
    Optional<LoanBook> findByBookIdAndCustomerId(Long bookId, Long customerId);
    List<LoanBook> findLoanBookByCustomerId(Long customerId);
    boolean existsByCustomerId(Long customerId);
    List<LoanBook> findLoanBookByBookId(Long bookId);
    List<LoanBook> findLoanBookByStatus(String status);
}
