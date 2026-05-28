package com.example.ktra.controller;

import com.example.ktra.model.dto.request.BookRequest;
import com.example.ktra.model.dto.response.ApiDataResponse;
import com.example.ktra.model.dto.response.BookResponse;
import com.example.ktra.service.IBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final IBookService bookService;
    @GetMapping
    public ResponseEntity<ApiDataResponse<List<BookResponse>>> getAllBooks() {
        return ResponseEntity.ok(
                new ApiDataResponse<>(
                        true,
                        "Lấy danh sách thành công",
                        bookService.getBooks()
                )
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<BookResponse>> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiDataResponse<>(
                        true,
                        "Lấy theo ID : " + id + " thành công",
                        bookService.getBookById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<BookResponse>> createBook(@Valid @RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(
                new ApiDataResponse<>(
                        true,
                        "Thêm dữ liệu thành công",
                        bookService.addBook(bookRequest)
                )
        );
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ApiDataResponse<BookResponse>> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(
                new ApiDataResponse<>(
                        true,
                        "Cập nhật thành công",
                        bookService.putBook(id,bookRequest)
                )
        );
    }

    @PostMapping("/{id}")
    public ResponseEntity<ApiDataResponse<BookResponse>> updateBook2(@PathVariable Long id, @Valid @RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(
                new ApiDataResponse<>(
                        true,
                        "Cập nhật thành công",
                        bookService.patchBook(id,bookRequest)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<BookResponse>> deleteBook(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiDataResponse<>(
                        true,
                        "Xóa thành công",
                        bookService.deleteBook(id)
                )
        );
    }
}
