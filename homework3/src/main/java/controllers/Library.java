package controllers;

import models.Book;


public class Library {

    private final Book[] books;

    public Library(Book[] books) {
        this.books = books;
    }

    protected Book get(int id) {
        return books[id];
    }

    protected void set(int id, Book book) {
        books[id] = book;
    }

    protected int getCapacity() {
        return books.length;
    }

    protected Book[] getBooks() {
        return books;
    }
}
