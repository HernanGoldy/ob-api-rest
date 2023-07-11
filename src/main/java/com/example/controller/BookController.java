package com.example.controller;

import com.example.entities.Book;
import com.example.repository.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    // atributos (características)
    private BookRepository bookRepository;

    // constructores
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // métodos / funciones (comportamientos)
    // CRUD sobre la entidad Book

    // Buscar todos los libros (lista de libros)
    @GetMapping("/api/books")
    public List<Book> findAll() {
        // Recuperar y devolver los libros en la base de datos
        return bookRepository.findAll();
    }

    // Buscar libros en base de datos según su id

    // Crear un nuevo libro en la base de datos

    // Actualizar un libro existente en la base de datos

    // Eliminar un libro en la base de datos


}
