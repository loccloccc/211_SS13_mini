package com.example.ktra.model.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookRequest {
    @NotBlank(message = "Không được để trống")
    private String title;
    @NotBlank(message = "Không được để trống")
    private String author;
    @NotBlank(message = "Không được để trống")
    private String category;
    @NotNull(message = "Không được để trống")
    @Min(value = 1 , message = "Số lượng không được để bé hơn 0")
    private Integer quantity;
}
