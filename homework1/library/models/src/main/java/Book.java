import lombok.Data;

@Data
public class Book {
    final private String title;
    final private Author author;
    final private int year;
}
