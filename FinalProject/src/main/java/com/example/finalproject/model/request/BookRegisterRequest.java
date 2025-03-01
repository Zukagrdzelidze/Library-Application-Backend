package com.example.finalproject.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRegisterRequest {

    private String title;
    private int  amount;


}
