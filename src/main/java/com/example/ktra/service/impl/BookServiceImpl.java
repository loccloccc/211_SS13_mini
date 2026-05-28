package com.example.ktra.service.impl;

import com.example.ktra.exception.ResourceNotFoundException;
import com.example.ktra.model.dto.request.BookRequest;
import com.example.ktra.model.dto.response.BookResponse;
import com.example.ktra.model.entity.Book;
import com.example.ktra.repository.IBookRepository;
import com.example.ktra.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {
    private final IBookRepository bookRepository;

    private BookResponse mapToBook(Book book) {
        return  new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getQuantity()
        );
    }

    @Override
    public List<BookResponse> getBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::mapToBook).toList();
    }

    @Override
    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id"));
        return mapToBook(book);
    }
    @Override
    public BookResponse addBook(BookRequest book) {
        Book newBook = new Book();
        newBook.setTitle(book.getTitle());
        newBook.setAuthor(book.getAuthor());
        newBook.setCategory(book.getCategory());
        newBook.setQuantity(book.getQuantity());
        bookRepository.save(newBook);

        return mapToBook(newBook);
    }

    @Override
    public BookResponse putBook(Long id, BookRequest book) {
        Book book1 = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id"));
        book1.setTitle(book.getTitle());
        book1.setAuthor(book.getAuthor());
        book1.setCategory(book.getCategory());
        book1.setQuantity(book.getQuantity());
        bookRepository.save(book1);
        return mapToBook(book1);
    }

    @Override
    public BookResponse patchBook(Long id, BookRequest book) {
        Book book1 = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id"));

        if (book.getAuthor() != null) {
            book1.setAuthor(book.getAuthor());
        }
        if (book.getCategory() != null) {
            book1.setCategory(book.getCategory());
        }
        if (book.getQuantity() != null) {
            book1.setQuantity(book.getQuantity());
        }
        if (book.getTitle() != null) {
            book1.setTitle(book.getTitle());
        }
        bookRepository.save(book1);
        return null;
    }

    @Override
    public BookResponse deleteBook(Long id) {
        bookRepository.deleteById(id);
        return null;
    }
}
