package com.example.finalproject.repository;

import com.example.finalproject.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Boolean existsByUserIdentification(String userIdentification);


}
