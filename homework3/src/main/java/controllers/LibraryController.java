package controllers;

import LibraryExceptions.LibraryException;
import com.google.gson.Gson;
import com.google.inject.Inject;
import libraries.BooksFactory;
import models.Book;

public class LibraryController {
    private final Library library;

    public LibraryController(int capacity, BooksFactory booksFactory) {
        library = booksFactory.books();

        if (library.getCapacity() > capacity)
            throw new LibraryException();


    }

    public LibraryController(String capacity, BooksFactory booksFactory) {
        library = booksFactory.library(Integer.parseInt(capacity));
    }

    public final static class Factory {
        private final BooksFactory booksFactory;

        @Inject
        public Factory(BooksFactory booksFactory) {
            this.booksFactory = booksFactory;
        }

        public LibraryController make(int capacity) {
            return new LibraryController(capacity, booksFactory);
        }

        public LibraryController makeCapacity(String capacityStr) {
            return new LibraryController(capacityStr, booksFactory);
        }
    }

    public Book getById(int id) {
        Book book = library.get(id);

        if (book == null)
            throw new LibraryException();

        Gson gson = new Gson();
        System.out.println("Id Position - " + id + "\n" + gson.toJson(book));
        return book;
    }

    public void addBook(Book book) {
        int count = 0;
        while (library.get(count) != null) {
            count++;
        }
        library.set(count, book);
    }

    public void showBooks() {
        Gson gson = new Gson();
        int length = library.getCapacity();
        for (int i = 0; i < length; i++) {
            System.out.println("Id Position - " + i + "\n" + gson.toJson(library.get(i)));
        }

    }
}

