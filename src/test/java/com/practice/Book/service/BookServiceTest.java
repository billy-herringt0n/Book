package com.practice.Book.service;

import com.practice.Book.model.Book;
import com.practice.Book.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private BookRepository bookRepository;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class); // создаём мок
        bookService = new BookService(bookRepository); // передаём мок в сервис
    }

    @Test
    void testFindAll() {
        Book b1 = new Book(1L, "Java", "Author1", 2020);
        Book b2 = new Book(2L, "Spring", "Author2", 2021);

        when(bookRepository.findAll()).thenReturn(Arrays.asList(b1, b2));

        List<Book> books = bookService.findAll();

        assertEquals(2, books.size());
        assertEquals("Java", books.get(0).getTitle());
        assertEquals("Spring", books.get(1).getTitle());
    }

    @Test
    void testFindById_found() {
        Book book = new Book(1L, "Java", "Author", 2020);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> result = bookService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Java", result.get().getTitle());
    }

    @Test
    void testFindById_notFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Book> result = bookService.findById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void testSave() {
        Book bookToSave = new Book(null, "New Book", "New Author", 2023);
        Book savedBook = new Book(1L, "New Book", "New Author", 2023);

        when(bookRepository.save(bookToSave)).thenReturn(savedBook);

        Book result = bookService.save(bookToSave);

        assertEquals(1L, result.getId());
        assertEquals("New Book", result.getTitle());
    }

    @Test
    void testDeleteById() {
        bookService.deleteById(1L);
        verify(bookRepository, times(1)).deleteById(1L); // проверяем вызов
    }
}
