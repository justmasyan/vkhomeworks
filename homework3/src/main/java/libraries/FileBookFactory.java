package libraries;

import LibraryExceptions.LibraryException;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import controllers.Library;
import models.Book;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class FileBookFactory implements BooksFactory {

    private static final Type listBooksType = new TypeToken<ArrayList<Book>>() {
    }.getType();
    private final String fileName;

    public FileBookFactory(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Library library(int capacity) {
        try {
            Collection<Book> collection = new Gson().fromJson(new BufferedReader(new FileReader(fileName)), listBooksType);
            if (collection.size() > capacity)
                throw new LibraryException();

            Book[] books = collection.toArray(Book[]::new);
            return (capacity != books.length) ? new Library(Arrays.copyOf(books, capacity)) : new Library(books);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}
