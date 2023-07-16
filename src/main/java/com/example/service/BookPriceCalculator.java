package com.example.service;

import com.example.entities.Book;

public class BookPriceCalculator {

    // métodos / funciones (comportamientos)
    public double calculatePrice(Book book) {

        double price = book.getPrice();

        if (book.getPrice() > 300) {
            price += 5;
        }

        // envío
        price += 2.99;
        return price;
    }
}
