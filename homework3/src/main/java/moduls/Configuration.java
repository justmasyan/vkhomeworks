package moduls;

import com.google.inject.AbstractModule;
import libraries.BooksFactory;
import libraries.FileBookFactory;

public class Configuration extends AbstractModule{
    private final String[] args;

    public Configuration(String[] args) {
        this.args = args;
    }

    @Override
    protected void configure() {
        bind(BooksFactory.class).toInstance(new FileBookFactory(args[0]));
    }
}
