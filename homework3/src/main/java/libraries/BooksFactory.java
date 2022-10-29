package libraries;

import controllers.Library;

public interface BooksFactory {

    Library books();

    Library library(int capacity);
}
