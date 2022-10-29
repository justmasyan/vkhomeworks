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

        public LibraryController make(String capacity, boolean limitation) {

            return (limitation) ? new LibraryController(Integer.parseInt(capacity), booksFactory) : new LibraryController(capacity, booksFactory);
        }

    }

    public Book getById(int id) {
        Book book = library.get(id);

        if (book == null)
            throw new LibraryException();

        Gson gson = new Gson();
        System.out.println("Id Position - " + id + "\n" + gson.toJson(book));
        library.set(id, null);
        return book;
    }

    public void addBook(Book book) {
        int count = 0;
        try {
            while (library.get(count) != null) {
                count++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new LibraryException();
        }
        library.set(count, book);
    }

    public void showBooks() {
        Gson gson = new Gson();
        int length = library.getCapacity();

        for (int i = 0; i < length; i++) {
            Book book = library.get(i);

            if (book != null)
                System.out.println("Id Position - " + i + "\n" + gson.toJson(library.get(i)));
        }
        System.out.println("Library capacity - " + length + "\n");
    }

    public Book[] getBooks() {
        return library.getBooks();
    }

}

