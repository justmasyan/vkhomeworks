import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FactoryLibrary {

    final static private String filename = "books.txt";
    final static private Library library;

    static {
        library = new Library();

        try (Scanner sc = new Scanner(new File(filename))) {

            while (sc.hasNextLine()) {
                library.addBook(new Book(sc.next(), new Author(sc.next(), sc.next()), sc.nextInt()));
            }
        } catch (FileNotFoundException exc) {
            System.out.println("Ошибка.Файл не найден");
        }

    }

    public static Library getLibrary(){
        return library;
    }

}
