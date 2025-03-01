package com.example.finalproject.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String author;

    private int numberOfPages;

    private int amount;

    @Builder.Default
    private boolean isActivated = true;

    private boolean isRefreshed;


}
