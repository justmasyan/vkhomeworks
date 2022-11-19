package dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.sql.Date;

import static dao.DataBaseInitializer.DEFAULT;

class DAOTest {

    //Необходимо создать тестовую базу данных
    final private DataBaseInitializer testDatabase = new DataBaseInitializer(
            DEFAULT.getCONNECTION(),
            DEFAULT.getDB_NAME() + "Test",
            DEFAULT.getUSERNAME(),
            DEFAULT.getPASSWORD()
    );

    @BeforeEach
    public void initDatabase() {
        testDatabase.initTestDatabase();
    }

    @Test
    void maxTenProviders() {
        Map<String, List<String>> exceptedMap = new HashMap<>();
        String[] array = new String[]{"VotchinaLogus"};
        exceptedMap.put("orangesoranges", Arrays.asList(array));
        exceptedMap.put("applesgreen", Arrays.asList(array));
        array = new String[]{"VitaKamin", "LogisGusi"};
        exceptedMap.put("bananayellow", Arrays.asList(array));
        array = new String[]{"VitaKamin", "FunnyArbuser", "VotchinaLogus"};
        exceptedMap.put("potatowild", Arrays.asList(array));
        array = new String[]{null};
        exceptedMap.put("pineappleblack", Arrays.asList(array));
        array = new String[]{"Antonvis", "ManorSirma", "Lamita", "Anton", "YourSirma", "Mivis", "Mivista", "HailOculus", "FunnyArbuser", "LogisGusi"};
        exceptedMap.put("tomatored", Arrays.asList(array));
        Assertions.assertEquals(exceptedMap, DAO.maxTenProviders(testDatabase));
    }

    @Test
    void priceBetterThan() {
        int exceptedAmount = 50;
        Map<String, List<String>> exceptedMap = new HashMap<>();

        String[] array = new String[]{"tomatored"};
        exceptedMap.put("Antonvis", Arrays.asList(array));
        exceptedMap.put("Anton", Arrays.asList(array));
        exceptedMap.put("Lamita", Arrays.asList(array));
        exceptedMap.put("FunnyArbuser", Arrays.asList(array));
        exceptedMap.put("ManorSirma", Arrays.asList(array));
        exceptedMap.put("Mivis", Arrays.asList(array));
        exceptedMap.put("YourSirma", Arrays.asList(array));
        exceptedMap.put("Mivista", Arrays.asList(array));
        exceptedMap.put("HailOculus", Arrays.asList(array));

        array = new String[]{"orangesoranges"};
        exceptedMap.put("VotchinaLogus", Arrays.asList(array));

        array = new String[]{"bananayellow", "potatowild"};
        exceptedMap.put("VitaKamin", Arrays.asList(array));

        Assertions.assertEquals(exceptedMap, DAO.priceBetterThan(testDatabase, exceptedAmount));

    }

    @Test
    void allProductsAmountAndSum() {
        Date exceptedStartPeriod = Date.valueOf("1910-01-01");
        Date exceptedFinishPeriod = Date.valueOf("1920-01-01");

        Map<String, List<Integer>> exceptedMap = new HashMap<>();

        Integer[] array = new Integer[]{75, 53400};
        exceptedMap.put("orangesoranges", Arrays.asList(array));
        array = new Integer[]{50, 26050};
        exceptedMap.put("applesgreen", Arrays.asList(array));
        array = new Integer[]{160, 17500};
        exceptedMap.put("bananayellow", Arrays.asList(array));
        array = new Integer[]{467, 87791};
        exceptedMap.put("potatowild", Arrays.asList(array));
        array = new Integer[]{0, 0};
        exceptedMap.put("pineappleblack", Arrays.asList(array));
        array = new Integer[]{2289, 401217};
        exceptedMap.put("tomatored", Arrays.asList(array));

        Assertions.assertEquals(exceptedMap, DAO.allProductsAmountAndSum(testDatabase, exceptedFinishPeriod, exceptedStartPeriod));
    }

    @Test
    void averagePriceProduct() {
        Date exceptedStartPeriod = Date.valueOf("1910-01-01");
        Date exceptedFinishPeriod = Date.valueOf("1914-01-01");

        Map<String, BigDecimal> exceptedMap = new HashMap<>();

        BigDecimal price = new BigDecimal("712.0000000000000000");
        exceptedMap.put("orangesoranges", price);
        price = new BigDecimal("250.0000000000000000");
        exceptedMap.put("bananayellow", price);
        price = new BigDecimal("521.0000000000000000");
        exceptedMap.put("applesgreen", price);
        price = new BigDecimal("33.0000000000000000");
        exceptedMap.put("potatowild", price);
        price = null;
        exceptedMap.put("pineappleblack", price);
        price = new BigDecimal("311.0000000000000000");
        exceptedMap.put("tomatored", price);

        Assertions.assertEquals(exceptedMap, DAO.averagePriceProduct(testDatabase, exceptedFinishPeriod, exceptedStartPeriod));
    }

    @Test
    void productsFromAllOrganization() {
        Date exceptedStartPeriod = Date.valueOf("1910-01-01");
        Date exceptedFinishPeriod = Date.valueOf("1916-01-01");

        Map<String, List<String>> exceptedMap = new HashMap<>();
        String[] array = new String[]{"nullnull"};
        exceptedMap.put("Antonvis", Arrays.asList(array));
        exceptedMap.put("Anton", Arrays.asList(array));
        exceptedMap.put("Mivis", Arrays.asList(array));
        exceptedMap.put("YourSirma", Arrays.asList(array));
        exceptedMap.put("HailOculus", Arrays.asList(array));
        exceptedMap.put("Lami", Arrays.asList(array));
        exceptedMap.put("ManorSirma", Arrays.asList(array));

        array = new String[]{"tomatored"};
        exceptedMap.put("Lamita", Arrays.asList(array));
        exceptedMap.put("Mivista", Arrays.asList(array));

        array = new String[]{"tomatored", "potatowild"};
        exceptedMap.put("FunnyArbuser", Arrays.asList(array));
        array = new String[]{"potatowild", "tomatored", "applesgreen", "orangesoranges"};
        exceptedMap.put("VotchinaLogus", Arrays.asList(array));
        array = new String[]{"tomatored", "bananayellow"};
        exceptedMap.put("LogisGusi", Arrays.asList(array));
        array = new String[]{"nullnull"};
        exceptedMap.put("VitaKamin", Arrays.asList(array));

        Assertions.assertEquals(exceptedMap, DAO.productsFromAllOrganization(testDatabase, exceptedStartPeriod, exceptedFinishPeriod));
    }
}