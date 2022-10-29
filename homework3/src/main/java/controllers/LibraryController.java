package controllers;

import LibraryExceptions.LibraryException;
import com.google.gson.Gson;
import com.google.inject.Inject;
import libraries.BooksFactory;
import models.Book;

public class LibraryController {
    private final Library library;

    public LibraryController(int capacity, BooksFactory booksFactory) {
        library = booksFactory.library(capacity);
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
        int limit = library.getCapacity();
        while (count < limit && library.get(count) != null)
            count++;

        if (count != limit)
            library.set(count, book);
        else
            throw new LibraryException();
    }

    public void showBooks() {
        Gson gson = new Gson();
        int length = library.getCapacity();
        int count = 0;
        for (int i = 0; i < length; i++) {
            Book book = library.get(i);

            if (book != null) {
                System.out.println("Id Position - " + i + "\n" + gson.toJson(library.get(i)));
                count++;
            }
        }
        System.out.println("Library capacity - " + length + "; Number of occupied places - " + count + ";\n");
    }

    public Book[] getBooks() {
        Book[] newLibrary = new Book[library.getCapacity()];
        for (int i = 0; i < library.getCapacity(); i++) {
            newLibrary[i] = library.get(i);
        }
        return newLibrary;
    }

}

