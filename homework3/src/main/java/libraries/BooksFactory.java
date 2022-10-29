package libraries;

import controllers.Library;

public interface BooksFactory {

    Library library(int capacity);
}
