package com.example.ktra.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private String category;
    private Integer quantity;
}
