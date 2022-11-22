package dao;

import models.Provider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static dao.DataBaseInitializer.DEFAULT;

class DaoProviderTest {

    final private DataBaseInitializer testDatabase = new DataBaseInitializer(
            DEFAULT.getCONNECTION(),
            DEFAULT.getDB_NAME() + "Test",
            DEFAULT.getUSERNAME(),
            DEFAULT.getPASSWORD()
    );
    DaoProvider dao = new DaoProvider();

    @BeforeEach
    public void initDatabase() {
        testDatabase.initTestDatabase();
    }

    @Test
    void get() {
        int exceptedId = 2;
        Provider exceptedProvider = new Provider("LogisGusi", "607636", "17253906");

        Provider actualProvider = dao.get(testDatabase, exceptedId);

        Assertions.assertEquals(exceptedProvider, actualProvider);
    }

    @Test
    void all() {
        List<Provider> exceptedList = new ArrayList<>();
        exceptedList.add(new Provider("VotchinaLogus", "418467", "50803760"));
        exceptedList.add(new Provider("LogisGusi", "607636", "17253906"));
        exceptedList.add(new Provider("VitaKamin", "239678", "32086753"));
        exceptedList.add(new Provider("FunnyArbuser", "777777", "77777777"));
        exceptedList.add(new Provider("Lami", "967207", "7300768"));
        exceptedList.add(new Provider("Lamita", "394966", "3831734"));
        exceptedList.add(new Provider("Mivista", "258199", "8196129"));
        exceptedList.add(new Provider("Mivis", "304512", "8327669"));
        exceptedList.add(new Provider("Antonvis", "337579", "8296773"));
        exceptedList.add(new Provider("Anton", "359161", "8926151"));
        exceptedList.add(new Provider("HailOculus", "793944", "6773964"));
        exceptedList.add(new Provider("ManorSirma", "635564", "6733333"));
        exceptedList.add(new Provider("YourSirma", "9812032", "2323964"));

        List<Provider> actualList = dao.all(testDatabase);

        Assertions.assertEquals(exceptedList, actualList);
    }

    @Test
    void insert() {
        int exceptedId = 14;
        Provider exceptedProvider = new Provider("GusiLebedi", "1241264124", "123412412");

        dao.insert(testDatabase, exceptedProvider);
        Provider actualProvider = dao.get(testDatabase, exceptedId);

        Assertions.assertEquals(exceptedProvider, actualProvider);
    }

    @Test
    void update() {
        int exceptedId = 1;
        Provider exceptedProvider = new Provider("GusiLebedi", "1241264124", "123412412");

        dao.update(testDatabase, exceptedId, exceptedProvider);
        Provider actualProvider = dao.get(testDatabase, exceptedId);

        Assertions.assertEquals(exceptedProvider, actualProvider);
    }

    @Test
    void delete() {
        int exceptedId = 4;
        Provider exceptedProvider = new Provider("FunnyArbuser", "777777", "77777777");

        dao.delete(testDatabase, exceptedProvider);
        Provider actualProvider = dao.get(testDatabase, exceptedId);

        Assertions.assertNull(actualProvider);
    }
}