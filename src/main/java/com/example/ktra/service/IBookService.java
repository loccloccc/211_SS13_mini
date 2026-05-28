package com.example.ktra.service;

import com.example.ktra.model.dto.request.BookRequest;
import com.example.ktra.model.dto.response.BookResponse;
import com.example.ktra.model.entity.Book;

import java.util.List;

public interface IBookService {
    List<BookResponse> getBooks();
    BookResponse getBookById(Long id);
    BookResponse addBook(BookRequest book);
    BookResponse putBook(Long id, BookRequest book);
    BookResponse patchBook(Long id, BookRequest book);
    BookResponse deleteBook(Long id);
}
