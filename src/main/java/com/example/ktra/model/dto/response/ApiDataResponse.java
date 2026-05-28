package com.example.ktra.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiDataResponse <T>{
    private boolean success;
    private String message;
    private T data;
}
