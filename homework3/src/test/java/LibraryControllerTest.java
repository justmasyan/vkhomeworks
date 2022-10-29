import LibraryExceptions.LibraryException;
import models.Author;
import com.google.inject.Inject;
import controllers.Library;
import controllers.LibraryController;
import libraries.BooksFactory;
import models.Book;
import moduls.ConfigurationTest;
import name.falgout.jeffrey.testing.junit.guice.IncludeModule;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@ExtendWith(MockitoExtension.class)
@IncludeModule(ConfigurationTest.class)
public class LibraryControllerTest {

    @Inject
    BooksFactory booksFactory;

    @Mock
    BooksFactory mock;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void setDownStream() {
        System.setOut(System.out);
    }

    @Test
    void notEnoughCapacity() {
        Assertions.assertThrows(LibraryException.class, () -> new LibraryController(20, booksFactory));
    }

    @Test
    void correctOrderBooks() {
        int magicNumber = 8;
        Mockito.when(mock.library(magicNumber)).thenReturn(new Library(new Book[]{
                new Book("Book 2", new Author("Author 1")),
                new Book("Book 4", new Author("Author 2")),
                null,
                new Book("Book 8", new Author("Author 4")),
                new Book("Book 10", new Author("Author 5")),
                null,
                null,
                new Book("Book 6", new Author("Author 3")),
        }));

        final var controller = new LibraryController(magicNumber, mock);
        Assertions.assertArrayEquals(new Book[]{
                new Book("Book 2", new Author("Author 1")),
                new Book("Book 4", new Author("Author 2")),
                null,
                new Book("Book 8", new Author("Author 4")),
                new Book("Book 10", new Author("Author 5")),
                null,
                null,
                new Book("Book 6", new Author("Author 3")),
        }, controller.getBooks());

    }

    @Test
    void bookInfo() {
        int magicNumber = 5;
        Mockito.when(mock.library(magicNumber)).thenReturn(new Library(new Book[]{null, null, new Book("Book 6", new Author("Author 6")), null, null}));
        final var controller = new LibraryController(magicNumber, mock);
        controller.getById(2);
        String excepted =
                "Id Position - 2\n" +
                "{\"name\":\"Book 6\",\"author\":{\"name\":\"Author 6\"}}\r\n";
        Assertions.assertEquals(excepted, outContent.toString());
    }

    @Test
    void takeEmptyPlace() {
        final var controller = new LibraryController(105, booksFactory);
        Assertions.assertThrows(LibraryException.class, () -> controller.getById(104));
    }

    @Test
    void takeCertainBook() {
        final var controller = new LibraryController(105, booksFactory);
        Book book = new Book("Book 4", new Author("Author0"));
        Assertions.assertEquals(book, controller.getById(4));
    }

    @Test
    void correctAddBook() {
        int magicNumber = 5;
        Mockito.when(mock.library(magicNumber)).thenReturn(new Library(new Book[]{null, null, new Book("Book 6", new Author("Author 6")), null, null}));
        final var controller = new LibraryController(magicNumber, mock);
        Book one = new Book("book1", new Author("author1"));
        Book two = new Book("book2", new Author("author2"));
        controller.addBook(one);
        Assertions.assertEquals(one, controller.getById(0));
        controller.addBook(two);
        Assertions.assertEquals(two, controller.getById(0));

    }

    @Test
    void notPlaceForBook() {
        int magicNumber = 3;
        Mockito.when(mock.library(magicNumber)).thenReturn(new Library(new Book[]{
                new Book("Book 2", new Author("Author 1")),
                new Book("Book 4", new Author("Author 2")),
                new Book("Book 6", new Author("Author 3"))
        }));
        final var controller = new LibraryController(magicNumber, mock);
        Assertions.assertThrows(LibraryException.class, () -> controller.addBook(new Book("new book", new Author("new author"))));
    }

    @Test
    void allLibraryInfo() {
        int magicNumber = 10;
        Mockito.when(mock.library(10)).thenReturn(new Library(new Book[]{
                new Book("Book 0", new Author("Author0")),
                new Book("Book 1", new Author("Author0")),
                new Book("Book 2", new Author("Author0")),
                new Book("Book 3", new Author("Author0")),
                new Book("Book 4", new Author("Author0")),
                null,
                new Book("Book 6",new Author("Author0")),
                null,
                null,
                null
        }));
        final var controller = new LibraryController(10, mock);
        controller.showBooks();
        String excepted =
                "Id Position - 0\n" +
                "{\"name\":\"Book 0\",\"author\":{\"name\":\"Author0\"}}\r\n" +
                "Id Position - 1\n" +
                "{\"name\":\"Book 1\",\"author\":{\"name\":\"Author0\"}}\r\n" +
                "Id Position - 2\n" +
                "{\"name\":\"Book 2\",\"author\":{\"name\":\"Author0\"}}\r\n" +
                "Id Position - 3\n" +
                "{\"name\":\"Book 3\",\"author\":{\"name\":\"Author0\"}}\r\n" +
                "Id Position - 4\n" +
                "{\"name\":\"Book 4\",\"author\":{\"name\":\"Author0\"}}\r\n" +
                "Id Position - 6\n" +
                "{\"name\":\"Book 6\",\"author\":{\"name\":\"Author0\"}}\r\n" +
                "Library capacity - 10; Number of occupied places - 6;\n\r\n";
        Assertions.assertEquals(excepted, outContent.toString());
    }

}
