package com.example.finalproject.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "LoanBook")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long bookId;

    private Long customerId;

    private String status;

    private int rating;
}
