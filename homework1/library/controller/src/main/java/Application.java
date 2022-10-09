import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Application {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Ошибка. Фамилия автора не введена");
            return;
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FactoryLibrary.getLibrary().getBooks().forEach(book -> {
            String fullname = book.getAuthor().getSecondName() + book.getAuthor().getFirstName();
            if (fullname.equals(args[0])) {
                System.out.println(gson.toJson(book));
            }

        });

    }
}
