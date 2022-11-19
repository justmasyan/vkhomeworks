package dao;

import models.InvoiceInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static dao.DataBaseInitializer.DEFAULT;

class DAOInvoiceInfoTest {

    final private DataBaseInitializer testDatabase = new DataBaseInitializer(
            DEFAULT.getCONNECTION(),
            DEFAULT.getDB_NAME() + "Test",
            DEFAULT.getUSERNAME(),
            DEFAULT.getPASSWORD()
    );
    DAOInvoiceInfo dao = new DAOInvoiceInfo();

    @BeforeEach
    public void initDatabase() {
        testDatabase.initTestDatabase();
    }

    @Test
    void get() {
        int exceptedId = 3;
        InvoiceInfo exceptedInvoice = new InvoiceInfo(Date.valueOf("1913-08-20"), 1);

        InvoiceInfo actualInvoice = dao.get(testDatabase, exceptedId);

        Assertions.assertEquals(exceptedInvoice, actualInvoice);
    }

    @Test
    void all() {
        List<InvoiceInfo> exceptedList = new ArrayList<>();
        exceptedList.add(new InvoiceInfo(Date.valueOf("1913-06-12"), 2));
        exceptedList.add(new InvoiceInfo(Date.valueOf("1913-06-12"), 1));
        exceptedList.add(new InvoiceInfo(Date.valueOf("1913-08-20"), 1));
        exceptedList.add(new InvoiceInfo(Date.valueOf("1915-03-04"), 4));
        exceptedList.add(new InvoiceInfo(Date.valueOf("1918-07-14"), 3));
        exceptedList.add(new InvoiceInfo(Date.valueOf("1912-07-14"), 6));
        exceptedList.add(new InvoiceInfo(Date.valueOf("1915-07-14"), 7));
        exceptedList.add(new InvoiceInfo(Date.valueOf("1918-07-14"), 8));
        exceptedList.add(new InvoiceInfo(Date.valueOf("1921-07-14"), 9));
        exceptedList.add(new InvoiceInfo(Date.valueOf("1924-07-14"), 10));
        exceptedList.add(new InvoiceInfo(Date.valueOf("1927-07-14"), 11));
        exceptedList.add(new InvoiceInfo(Date.valueOf("1929-07-14"), 12));
        exceptedList.add(new InvoiceInfo(Date.valueOf("1931-07-14"), 13));

        List<InvoiceInfo> actualList = dao.all(testDatabase);

        Assertions.assertEquals(exceptedList, actualList);
    }

    @Test
    void insert() {
        int exceptedId = 14;
        InvoiceInfo exceptedInvoice = new InvoiceInfo(Date.valueOf("2222-05-10"), 3);

        dao.insert(testDatabase, exceptedInvoice);
        InvoiceInfo actualInvoice = dao.get(testDatabase, exceptedId);

        Assertions.assertEquals(exceptedInvoice, actualInvoice);
    }

    @Test
    void update() {
        int exceptedId = 1;
        InvoiceInfo exceptedInvoice = new InvoiceInfo(Date.valueOf("2222-05-10"), 3);

        dao.update(testDatabase, exceptedId, exceptedInvoice);
        InvoiceInfo actualInvoice = dao.get(testDatabase, exceptedId);

        Assertions.assertEquals(exceptedInvoice, actualInvoice);
    }

    @Test
    void delete() {
        int exceptedId = 4;
        InvoiceInfo exceptedInvoice = new InvoiceInfo(Date.valueOf("1915-03-04"), 4);

        dao.delete(testDatabase, exceptedInvoice);
        InvoiceInfo actualInvoice = dao.get(testDatabase, exceptedId);

        Assertions.assertNull(actualInvoice);
    }
}