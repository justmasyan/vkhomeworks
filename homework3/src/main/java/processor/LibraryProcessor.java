package processor;


import com.google.inject.Inject;
import controllers.LibraryController;

public class LibraryProcessor {
    private final LibraryController.Factory libraryFactory;

    @Inject
    public LibraryProcessor(LibraryController.Factory libraryFactory) {
        this.libraryFactory = libraryFactory;
    }

    public LibraryController getLibraryController(int capacity) {
        return libraryFactory.make(capacity);
    }

}
