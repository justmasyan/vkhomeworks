import LibraryExceptions.LibraryException;
import models.Author;
import com.google.inject.Inject;
import controllers.Library;
import controllers.LibraryController;
import libraries.BooksFactory;
import models.Book;
import moduls.ConfigurationTest;
import myextensions.MyExtension;
import name.falgout.jeffrey.testing.junit.guice.IncludeModule;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@ExtendWith(MyExtension.class)
@IncludeModule(ConfigurationTest.class)
public class LibraryControllerTest {

    @Inject
    BooksFactory booksFactory;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUpStream(){
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void setDownStream(){
        System.setOut(System.out);
    }


    @Test
    void notEnoughCapacity(){
        final var mock = Mockito.mock(BooksFactory.class);
        Mockito.when(mock.books()).thenReturn(new Library(new Book[17]));
        Assertions.assertThrows(LibraryException.class, () -> new LibraryController(5, mock));
    }

    @Test
    void correctOrderBooks(){
        final var mock = Mockito.mock(BooksFactory.class);
        Mockito.when(mock.library(Mockito.anyInt())).thenReturn(new Library(new Book[]{
                new Book("Book 2",new Author("Author 1")),
                new Book("Book 4",new Author("Author 2")),
                null,
                new Book("Book 8",new Author("Author 4")),
                new Book("Book 10",new Author("Author 5")),
                null,
                null,
                new Book("Book 6",new Author("Author 3")),
        }));

        final var controller = new LibraryController("8",mock);
        controller.showBooks();
        String excepted = "Id Position - 0\n" +
                "{\"name\":\"Book 2\",\"author\":{\"name\":\"Author 1\"}}\r\n" +
                "Id Position - 1\n" +
                "{\"name\":\"Book 4\",\"author\":{\"name\":\"Author 2\"}}\r\n" +
                "Id Position - 2\n" +
                "null\r\n" +
                "Id Position - 3\n" +
                "{\"name\":\"Book 8\",\"author\":{\"name\":\"Author 4\"}}\r\n" +
                "Id Position - 4\n" +
                "{\"name\":\"Book 10\",\"author\":{\"name\":\"Author 5\"}}\r\n" +
                "Id Position - 5\n" +
                "null\r\n" +
                "Id Position - 6\n" +
                "null\r\n" +
                "Id Position - 7\n" +
                "{\"name\":\"Book 6\",\"author\":{\"name\":\"Author 3\"}}\r\n";
        String actual = outContent.toString();
        Assertions.assertEquals(excepted,outContent.toString());
    }

    @Test
    void bookInfo(){
        final var mock = Mockito.mock(BooksFactory.class);
        Mockito.when(mock.books()).thenReturn(new Library(new Book[]{null,null,new Book("Book 6",new Author("Author 6")),null,null}));
        final var controller = new LibraryController(5,mock);
        controller.getById(2);
        String excepted = "Id Position - 2\n" +
                "{\"name\":\"Book 6\",\"author\":{\"name\":\"Author 6\"}}\r\n";
        Assertions.assertEquals(excepted,outContent.toString());
    }

    @Test
    void takeEmptyPlace(){
        final var controller = new LibraryController("105",booksFactory);
        Assertions.assertThrows(LibraryException.class, () -> controller.getById(104));
    }

    @Test
    void takeCertainBook(){
        final var controller = new LibraryController(105,booksFactory);
        Book book = new Book("Book 4",new Author("Author0"));
        Assertions.assertEquals(book,controller.getById(4));
    }

    @Test
    void correctAddBook(){
        final var mock = Mockito.mock(BooksFactory.class);
        Mockito.when(mock.books()).thenReturn(new Library(new Book[]{null,null,new Book("Book 6",new Author("Author 6")),null,null}));
        final var controller = new LibraryController(5,mock);
        Book one = new Book("book1",new Author("author1"));
        Book two = new Book("book2",new Author("author2"));
        controller.addBook(one);
        Assertions.assertEquals(one,controller.getById(0));
        controller.addBook(two);
        Assertions.assertEquals(two,controller.getById(1));

    }

    @Test
    void NotPlaceForBook(){
        final var mock = Mockito.mock(BooksFactory.class);
        Mockito.when(mock.books()).thenReturn(new Library(new Book[]{
                new Book("Book 2", new Author("Author 1")),
                new Book("Book 4", new Author("Author 2")),
                new Book("Book 6", new Author("Author 3"))
        }));
        final var controller = new LibraryController(3,mock);
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> controller.addBook(new Book("new book",new Author("new author"))));
    }

    @Test
    void allLibraryInfo(){
        final var controller = new LibraryController("10",booksFactory);
        controller.showBooks();
        String excepted = "Id Position - 0\n" +
                "{\"name\":\"Book 0\",\"author\":{\"name\":\"Author0\"}}\r\n" +
                "Id Position - 1\n" +
                "{\"name\":\"Book 1\",\"author\":{\"name\":\"Author0\"}}\r\n" +
                "Id Position - 2\n" +
                "{\"name\":\"Book 2\",\"author\":{\"name\":\"Author0\"}}\r\n" +
                "Id Position - 3\n" +
                "{\"name\":\"Book 3\",\"author\":{\"name\":\"Author0\"}}\r\n" +
                "Id Position - 4\n" +
                "{\"name\":\"Book 4\",\"author\":{\"name\":\"Author0\"}}\r\n" +
                "Id Position - 5\n" +
                "{\"name\":\"Book 5\",\"author\":{\"name\":\"Author0\"}}\r\n" +
                "Id Position - 6\n" +
                "{\"name\":\"Book 6\",\"author\":{\"name\":\"Author0\"}}\r\n" +
                "Id Position - 7\n" +
                "{\"name\":\"Book 7\",\"author\":{\"name\":\"Author0\"}}\r\n" +
                "Id Position - 8\n" +
                "{\"name\":\"Book 8\",\"author\":{\"name\":\"Author0\"}}\r\n" +
                "Id Position - 9\n" +
                "{\"name\":\"Book 9\",\"author\":{\"name\":\"Author0\"}}\r\n";
        Assertions.assertEquals(excepted,outContent.toString());
    }

}
