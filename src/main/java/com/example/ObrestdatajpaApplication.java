package com.example;

import com.example.entities.Book;
import com.example.repository.BookRepository;
import org.aspectj.apache.bcel.Repository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ObrestdatajpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ObrestdatajpaApplication.class, args);
		BookRepository repository = (BookRepository) context.getBean(Repository.class);

		// CRUD
		// crear un libro
		Book book1 = new Book(null, "La Odicea", "Homero", 99, 100.10, LocalDate.of(1020, 12, 10), true);
		Book book2 = new Book(null, "Paseando", "Jonh Peter", 250, 54.35, LocalDate.of(2018, 9, 22), true);

		// almacenar un libro
		System.out.printf("Número de libros en la base de datos. " + repository.findAll().size());

		repository.save(book1);
        repository.save(book2);

		// recuperar todos los libros
		System.out.printf("Número de libros en la base de datos. " + repository.findAll().size());

		// borrar un libro
		// repository.deleteById(1L);

		System.out.printf("Número de libros en la base de datos. " + repository.findAll().size());
	}
}
