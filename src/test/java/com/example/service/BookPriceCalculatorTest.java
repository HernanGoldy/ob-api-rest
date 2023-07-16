package com.example.service;

import com.example.entities.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    @Test
    void calculatePriceTest() {

        // 1. configuración de la prueba
        Book book = new Book(1L, "Java - Cómo Programar", "Paul Deitel, Harvey Deitel", 472, 50.23, LocalDate.now(), true);
        BookPriceCalculator calculator = new BookPriceCalculator();

        // 2. se ejecuta el comportamiento a testear
        double price = calculator.calculatePrice(book);
        System.out.println(price);

        // 3. comprobaciones / aserciones
        assertTrue(price > 0);
        assertEquals(53.22, price);
    }
}