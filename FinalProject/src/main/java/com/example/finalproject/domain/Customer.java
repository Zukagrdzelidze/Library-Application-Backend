package com.example.finalproject.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String userIdentification;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
