package com.practice.Book.service;

import com.practice.Book.model.Book;
import com.practice.Book.repository.BookRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void  deleteById(Long id) {
        bookRepository.deleteById(id);
    }

}
