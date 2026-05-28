package com.example.ktra.service;

import com.example.ktra.exception.ResourceNotFoundException;
import com.example.ktra.model.dto.request.BookRequest;
import com.example.ktra.model.dto.response.BookResponse;
import com.example.ktra.model.entity.Book;
import com.example.ktra.repository.IBookRepository;
import com.example.ktra.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private IBookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    // =====================================================
    // getAllBooks_returnList
    // =====================================================

    @Test
    void getAllBooks_returnList() {

        Book book1 = new Book(
                1L,
                "Java",
                "Loc",
                "IT",
                10
        );

        Book book2 = new Book(
                2L,
                "Spring",
                "Duong",
                "Backend",
                20
        );

        when(bookRepository.findAll())
                .thenReturn(List.of(book1, book2));

        List<BookResponse> result = bookService.getBooks();

        assertEquals(2, result.size());

        verify(bookRepository, times(1))
                .findAll();
    }

    // =====================================================
    // getBookById_found
    // =====================================================

    @Test
    void getBookById_found() {

        Book book = new Book(
                1L,
                "Java",
                "Loc",
                "IT",
                10
        );

        when(bookRepository.findById(1L))
                .thenReturn(Optional.of(book));

        BookResponse result =
                bookService.getBookById(1L);

        assertNotNull(result);

        assertEquals("Java", result.getTitle());

        verify(bookRepository, times(1))
                .findById(1L);
    }

    // =====================================================
    // getBookById_notFound
    // =====================================================

    @Test
    void getBookById_notFound() {

        when(bookRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> bookService.getBookById(1L)
        );

        verify(bookRepository, times(1))
                .findById(1L);
    }

    // =====================================================
    // createBook_success
    // =====================================================

    @Test
    void createBook_success() {

        BookRequest request = new BookRequest(
                "Java",
                "Loc",
                "IT",
                10
        );

        Book savedBook = new Book(
                1L,
                "Java",
                "Loc",
                "IT",
                10
        );

        when(bookRepository.save(any(Book.class)))
                .thenReturn(savedBook);

        BookResponse result =
                bookService.addBook(request);

        assertNotNull(result);

        assertEquals("Java", result.getTitle());

        verify(bookRepository, times(1))
                .save(any(Book.class));
    }

    // =====================================================
    // deleteBook_notFound
    // =====================================================

    @Test
    void deleteBook_notFound() {

        when(bookRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> bookService.deleteBook(1L)
        );

        verify(bookRepository, never())
                .delete(any(Book.class));
    }
}

