import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String inputname = input.nextLine();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FactoryLibrary.getLibrary().getBooks().forEach(book -> {
            String fullname = book.getAuthor().getSecondName() + " " + book.getAuthor().getFirstName();
            if (fullname.equals(inputname)) {
                System.out.println(gson.toJson(book));
            }

        });

    }
}
