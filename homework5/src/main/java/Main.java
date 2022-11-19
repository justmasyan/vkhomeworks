import dao.*;
import models.InvoiceData;
import models.InvoiceInfo;
import models.Product;
import models.Provider;

import java.sql.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            DAO dao;
            while (true) {
                System.out.println("Waiting for command. Key in Ctrl+D to exit. Example: \"-help\"");
                String command = scanner.nextLine();
                switch (command) {
                    case "-help" -> System.out.println("-help," +
                            "-addProvider," +
                            "-addProduct," +
                            "-maxTenProviders," +
                            "-priceBetterThan," +
                            "-allProductsAmountAndSum" +
                            "-averagePriceProduct" +
                            "-productsFromAllOrganization");
                    case "-initDefaultDatabase" -> DataBaseInitializer.initDefaultDatabase();
                    case "-addProvider" -> {
                        dao = new DaoProvider();
                        System.out.println("Input title,TIN,payment_account for Provider");
                        Provider provider = new Provider(scanner.next(), scanner.next(), scanner.next());
                        System.out.println("Принято");
                        dao.insert(DataBaseInitializer.DEFAULT, provider);
                    }
                    case "-addProduct" -> {
                        dao = new DaoProduct();
                        System.out.println("Input title,variety for Product");
                        Product product = new Product(scanner.next(), scanner.next());
                        dao.insert(DataBaseInitializer.DEFAULT, product);
                    }
                    case "-addInvoice" -> {
                        dao = new DAOInvoiceInfo();
                        System.out.println("Input date,provider for Invoice");
                        InvoiceInfo invoiceInfo = new InvoiceInfo(Date.valueOf(scanner.next()), scanner.nextInt());
                        dao.insert(DataBaseInitializer.DEFAULT, invoiceInfo);

                        int check;
                        System.out.println("Input productId,price,amount for Invoice (for different products on different lines). \nPrint -1 for continue");
                        dao = new DAOInvoiceData();
                        while ((check = scanner.nextInt()) != -1) {
                            InvoiceData invoiceData = new InvoiceData(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
                            dao.insert(DataBaseInitializer.DEFAULT, invoiceData);
                        }
                    }
                    case "-maxTenProviders" -> System.out.println(DAO.maxTenProviders(DataBaseInitializer.DEFAULT));
                    case "-priceBetterThan" -> {
                        System.out.println("Input amount.");
                        System.out.println(DAO.priceBetterThan(DataBaseInitializer.DEFAULT, scanner.nextInt()));
                    }
                    case "-allProductsAmountAndSum" -> {
                        System.out.println("Input the start and end dates of the period on two different lines. Format(yyyy-mm-dd)");
                        System.out.println(DAO.allProductsAmountAndSum(DataBaseInitializer.DEFAULT, Date.valueOf(scanner.nextLine()), Date.valueOf(scanner.nextLine())));
                    }
                    case "-averagePriceProduct" -> {
                        System.out.println("Input the start and end dates of the period on two different lines. Format(yyyy-mm-dd)");
                        System.out.println(DAO.averagePriceProduct(DataBaseInitializer.DEFAULT, Date.valueOf(scanner.nextLine()), Date.valueOf(scanner.nextLine())));
                    }
                    case "-productsFromAllOrganization" -> {
                        System.out.println("Input the start and end dates of the period on two different lines. Format(yyyy-mm-dd)");
                        System.out.println(DAO.productsFromAllOrganization(DataBaseInitializer.DEFAULT, Date.valueOf(scanner.nextLine()), Date.valueOf(scanner.nextLine())));
                    }
                }
            }
        } catch (IllegalStateException | NoSuchElementException e) {
        }
    }
}
