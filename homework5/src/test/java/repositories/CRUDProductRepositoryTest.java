package repositories;

import models.Invoice;
import models.Product;
import models.Provider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class CRUDProductRepositoryTest {

    //Необходимо создать тестовую базу данных

    final private DataBaseInitializer testDatabase = new DataBaseInitializer(
            "localhost",
            "5432",
            "ReportProductsTest",
            "postgres",
            "postgres"
    );

    @BeforeAll
    public static void initDatabase() {
        DataBaseInitializer.initTestDatabase();
    }

    @Test
    void addProduct() {
        Product exceptedProduct = new Product("carrot","long");
        CRUDProductRepository.addProduct(testDatabase,exceptedProduct);
        int exceptedId = 7;
        Assertions.assertEquals(exceptedProduct,CRUDProductRepository.getProductById(testDatabase,exceptedId).get(exceptedId));
    }

    @Test
    void addProvider() {
        Provider exceptedProvider = new Provider("DjVanya","0505050","5050505");
        CRUDProductRepository.addProvider(testDatabase,exceptedProvider);

        Assertions.assertEquals(exceptedProvider,CRUDProductRepository.getProviderByTitle(testDatabase,"DjVanya").get(1));
    }

    @Test
    void addInvoice() {
        int exceptedId = 6;
        Invoice exceptedInvoice = new Invoice(Date.valueOf("1919-05-05"),"FunnyArbuser",new int[]{5,6},new int[]{450,650},new int[]{1000,2000});
        CRUDProductRepository.addInvoice(testDatabase,exceptedInvoice);

        Assertions.assertEquals(exceptedInvoice,CRUDProductRepository.getInvoiceById(testDatabase,exceptedId).get(exceptedId));
    }

    @Test
    void getProviderByTitle() {
        Provider exceptedProvider = new Provider("Kolpak","34536","456456");
        Assertions.assertEquals(exceptedProvider,CRUDProductRepository.getProviderByTitle(testDatabase,"Kolpak").get(1));
    }

    @Test
    void getProductById() {
        int exceptedId = 3;
        Product exceptedProduct = new Product("tomato","red");
        Assertions.assertEquals(exceptedProduct,CRUDProductRepository.getProductById(testDatabase,exceptedId).get(exceptedId));
    }

    @Test
    void getInvoiceById() {
        int exceptedId = 2;
        Invoice exceptedInvoice = new Invoice(Date.valueOf("1913-06-12"),"VotchinaLogus",new int[]{3,4},new int[]{333,33},new int[]{13,31});
        Assertions.assertEquals(exceptedInvoice,CRUDProductRepository.getInvoiceById(testDatabase,2).get(2));
    }
}