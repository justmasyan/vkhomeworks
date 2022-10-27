package mainpackage;

import LibraryExceptions.LibraryException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import moduls.Configuration;
import processor.LibraryProcessor;

public class Main {
    public static void main(String[] args) throws LibraryException {
        final Injector injector = Guice.createInjector(new Configuration(args));
        injector.getInstance(LibraryProcessor.class).getLibraryControllerCapacity(args[1]).showBooks();
    }
}
