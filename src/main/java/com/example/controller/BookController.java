package com.example.controller;

import com.example.entities.Book;
import com.example.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; // permite mostrar mensajes con colores
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private final Logger log = LoggerFactory.getLogger(BookController.class);

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

    // ----------------------------------------------------------------

    // Buscar un solo libro en base de datos según su id
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findOneById(@PathVariable Long id) {

        Optional<Book> bookOpt = bookRepository.findById(id);
        // opción 1
         if (bookOpt.isPresent()) {
             return ResponseEntity.ok(bookOpt.get());
         } else {
             return ResponseEntity.notFound().build();
         }

        // opción 2
//        return bookOpt.orElse(null);
    }

    // ----------------------------------------------------------------

    // Crear un nuevo libro en la base de datos
    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers) {
        System.out.println(headers.get("User-Agent"));
        // guardar el libro recibido por parámetro en la base de datos
        if (book.getId() != null) { // quiere decir que exsite el id y por lo tanto no es una creación
            log.warn("Trying to create a book with id");
            System.out.println("trying to create a book with id");
            return ResponseEntity.badRequest().build(); // el libro devuelto tiene una clave primaria
        }

        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }

    // ----------------------------------------------------------------

    // Actualizar un libro existente en la base de datos
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book) {
        if (book.getId() == null) { // si no tiene id quiere decir que sí es una creación
            log.warn("Trying to update a non existent book");
            System.out.println("trying to update a non existent book");
            return ResponseEntity.badRequest().build();
        }

        if (!bookRepository.existsById(book.getId())) {
            log.warn("Trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }

        // El proceso de actualización
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result); // el libro devuelto tiene una clave primaria
    }

    // ----------------------------------------------------------------

    // Eliminar un libro de la base de datos
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id) {

        if (!bookRepository.existsById(id)) {
            log.warn("Trying to delete a non existent book");
            return ResponseEntity.notFound().build();
        }

        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Eliminar todos los libros de la base de datos
    @DeleteMapping("/api/books")
    public ResponseEntity<Book> deleteAll() {
        log.warn("REST Request for delete all books");
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
