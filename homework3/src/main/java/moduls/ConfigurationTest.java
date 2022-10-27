package moduls;

import com.google.inject.AbstractModule;
import libraries.BooksFactory;
import libraries.FileBookFactory;

public class ConfigurationTest extends AbstractModule {

    final private static String str = "books.txt";

    @Override
    protected void configure() {
        bind(BooksFactory.class).toInstance(new FileBookFactory(str));
    }
}
