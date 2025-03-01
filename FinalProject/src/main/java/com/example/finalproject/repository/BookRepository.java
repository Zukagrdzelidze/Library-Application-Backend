package com.example.finalproject.repository;

import com.example.finalproject.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.isRefreshed = true")
    List<Book> findRefreshedBooks();

    @Query("SELECT b FROM Book b WHERE b.isRefreshed = false")
    List<Book> findUnrefreshedBooks();

    boolean existsByTitle(String title);
}
