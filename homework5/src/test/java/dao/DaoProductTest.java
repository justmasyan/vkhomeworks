package dao;

import models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static dao.DataBaseInitializer.DEFAULT;

class DaoProductTest {

    //Необходимо создать тестовую базу данных
    final private DataBaseInitializer testDatabase = new DataBaseInitializer(
            DEFAULT.getCONNECTION(),
            DEFAULT.getDB_NAME() + "Test",
            DEFAULT.getUSERNAME(),
            DEFAULT.getPASSWORD()
    );
    DaoProduct dao = new DaoProduct();

    @BeforeEach
    public void initDatabase() {
        testDatabase.initTestDatabase();
    }

    @Test
    void get() {
        int exceptedId = 3;
        Product exceptedProduct = new Product("tomato", "red");

        Product actualProduct = dao.get(testDatabase, exceptedId);

        Assertions.assertEquals(exceptedProduct, actualProduct);
    }

    @Test
    void all() {
        List<Product> exceptedList = new ArrayList<>();
        exceptedList.add(new Product("apples", "green"));
        exceptedList.add(new Product("banana", "yellow"));
        exceptedList.add(new Product("tomato", "red"));
        exceptedList.add(new Product("potato", "wild"));
        exceptedList.add(new Product("oranges", "oranges"));
        exceptedList.add(new Product("pineapple", "black"));

        List<Product> actualList = dao.all(testDatabase);

        Assertions.assertEquals(exceptedList, actualList);
    }

    @Test
    void insert() {
        int exceptedId = 7;
        Product exceptedProduct = new Product("carrot", "long");

        dao.insert(testDatabase, exceptedProduct);
        Product actualProduct = dao.get(testDatabase, exceptedId);

        Assertions.assertEquals(exceptedProduct, actualProduct);
    }

    @Test
    void update() {
        int exceptedId = 1;
        Product exceptedProduct = new Product("carrot", "long");

        dao.update(testDatabase, exceptedId, exceptedProduct);
        Product actualProduct = dao.get(testDatabase, exceptedId);

        Assertions.assertEquals(exceptedProduct, actualProduct);
    }

    @Test
    void delete() {
        int exceptedId = 4;
        Product exceptedProduct = new Product("potato", "wild");

        dao.delete(testDatabase, exceptedProduct);
        Product actualProduct = dao.get(testDatabase, exceptedId);

        Assertions.assertNull(actualProduct);
    }
}