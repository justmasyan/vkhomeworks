package models;

import lombok.Data;

@Data
public class Book {
    private final String name;
    private final Author author;
}
