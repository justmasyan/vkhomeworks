package dao;

import models.InvoiceData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static dao.DataBaseInitializer.DEFAULT;

class DAOInvoiceDataTest {

    //Необходимо создать тестовую базу данных
    final private DataBaseInitializer testDatabase = new DataBaseInitializer(
            DEFAULT.getCONNECTION(),
            DEFAULT.getDB_NAME() + "Test",
            DEFAULT.getUSERNAME(),
            DEFAULT.getPASSWORD()
    );
    DAOInvoiceData dao = new DAOInvoiceData();

    @BeforeEach
    public void initDatabase() {
        testDatabase.initTestDatabase();
    }

    @Test
    void get() {
        int exceptedId = 3;
        InvoiceData exceptedInvoice = new InvoiceData(2, 3, 333, 13);

        InvoiceData actualInvoice = dao.get(testDatabase, exceptedId);

        Assertions.assertEquals(exceptedInvoice, actualInvoice);
    }

    @Test
    void all() {
        List<InvoiceData> exceptedList = new ArrayList<>();
        exceptedList.add(new InvoiceData(1, 2, 250, 10));
        exceptedList.add(new InvoiceData(1, 3, 500, 20));
        exceptedList.add(new InvoiceData(2, 3, 333, 13));
        exceptedList.add(new InvoiceData(2, 4, 33, 31));
        exceptedList.add(new InvoiceData(3, 1, 521, 50));
        exceptedList.add(new InvoiceData(3, 5, 712, 75));
        exceptedList.add(new InvoiceData(4, 3, 123, 56));
        exceptedList.add(new InvoiceData(4, 4, 188, 36));
        exceptedList.add(new InvoiceData(5, 2, 100, 150));
        exceptedList.add(new InvoiceData(5, 4, 200, 400));
        exceptedList.add(new InvoiceData(6, 3, 100, 1200));
        exceptedList.add(new InvoiceData(7, 3, 200, 400));
        exceptedList.add(new InvoiceData(8, 3, 300, 600));
        exceptedList.add(new InvoiceData(9, 3, 400, 1600));
        exceptedList.add(new InvoiceData(10, 3, 500, 1000));
        exceptedList.add(new InvoiceData(11, 3, 600, 200));
        exceptedList.add(new InvoiceData(12, 3, 700, 1400));
        exceptedList.add(new InvoiceData(13, 3, 800, 800));

        List<InvoiceData> actualList = dao.all(testDatabase);

        Assertions.assertEquals(exceptedList, actualList);
    }

    @Test
    void insert() {
        int exceptedId = 19;
        InvoiceData exceptedInvoice = new InvoiceData(3, 2, 350, 350);

        dao.insert(testDatabase, exceptedInvoice);
        InvoiceData actualInvoice = dao.get(testDatabase, exceptedId);

        Assertions.assertEquals(exceptedInvoice, actualInvoice);
    }

    @Test
    void update() {
        int exceptedId = 1;
        InvoiceData exceptedInvoice = new InvoiceData(3, 2, 350, 350);

        dao.update(testDatabase, exceptedId, exceptedInvoice);
        InvoiceData actualInvoice = dao.get(testDatabase, exceptedId);

        Assertions.assertEquals(exceptedInvoice, actualInvoice);
    }

    @Test
    void delete() {
        int exceptedId = 4;
        InvoiceData exceptedInvoice = new InvoiceData(2, 4, 33, 31);

        dao.delete(testDatabase, exceptedInvoice);
        InvoiceData actualInvoice = dao.get(testDatabase, exceptedId);

        Assertions.assertNull(actualInvoice);
    }
}