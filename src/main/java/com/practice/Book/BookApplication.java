package com.practice.Book;

import com.practice.Book.model.Book;
import com.practice.Book.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

	@Bean
	public CommandLineRunner test(BookService bookService) {
		return args -> {
			// Создаём и сохраняем книги
			bookService.save(new Book("Java Basics", "John Doe", 2020));
			bookService.save(new Book("Spring Boot", "Jane Smith", 2021));
			bookService.save(new Book("Advanced Java", "Alice Johnson", 2022));

			// Вывод всех книг
			System.out.println("Все книги:");
			bookService.findAll().forEach(book ->
					System.out.println(book.getId() + ": " + book.getTitle() + " by " + book.getAuthor())
			);

			// Поиск по ID
			System.out.println("\nКнига с ID = 2:");
			bookService.findById(2L).ifPresent(book ->
					System.out.println(book.getTitle() + " — " + book.getAuthor())
			);

			// Удаление книги
			bookService.deleteById(1L);
			System.out.println("\nПосле удаления книги с ID = 1:");
			bookService.findAll().forEach(book ->
					System.out.println(book.getId() + ": " + book.getTitle())
			);
		};
	}
}
