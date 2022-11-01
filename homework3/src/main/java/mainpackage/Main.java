package mainpackage;

import LibraryExceptions.LibraryException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import models.Author;
import models.Book;
import moduls.Configuration;
import processor.LibraryProcessor;

public class Main {
    public static void main(String[] args) throws LibraryException {
        final Injector injector = Guice.createInjector(new Configuration(args));
        final var controller = injector.getInstance(LibraryProcessor.class).getLibraryController(Integer.parseInt(args[1]));
        Book newBook = new Book("newBook", new Author("Hrenov"));
        controller.addBook(newBook);
        controller.getById(95);
        controller.showBooks();

    }
}
