import models.Invoice;
import models.Product;
import models.Provider;
import repositories.DataBaseInitializer;
import repositories.CRUDProductRepository;
import repositories.ReportsProductRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

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
                        System.out.println("Input title,TIN,payment_account for Provider");
                        Provider provider = new Provider(scanner.next(), scanner.next(), scanner.next());
                        System.out.println("Принято");
                        CRUDProductRepository.addProvider(DataBaseInitializer.DEFAULT, provider);
                    }
                    case "-addProduct" -> {
                        System.out.println("Input title,variety for Product");
                        Product product = new Product(scanner.next(), scanner.next());
                        CRUDProductRepository.addProduct(DataBaseInitializer.DEFAULT, product);
                    }
                    case "-addInvoice" -> {
                        System.out.println("Input date,provider for Invoice");
                        Date date = Date.valueOf(scanner.next());
                        String provider = scanner.next();
                        List<Integer> productsId = new ArrayList<>();
                        List<Integer> prices = new ArrayList<>();
                        List<Integer> amount = new ArrayList<>();

                        int check;
                        System.out.println("Input productId,price,amount for Invoice (for different products on different lines). \nPrint -1 for continue");
                        while ((check = scanner.nextInt()) != -1) {
                            productsId.add(check);
                            prices.add(scanner.nextInt());
                            amount.add(scanner.nextInt());
                        }

                        CRUDProductRepository.addInvoice(DataBaseInitializer.DEFAULT,
                                new Invoice(date, provider,
                                        productsId.stream().mapToInt(i -> i).toArray(),
                                        prices.stream().mapToInt(i -> i).toArray(),
                                        amount.stream().mapToInt(i -> i).toArray()));
                    }
                    case "-maxTenProviders" -> System.out.println(ReportsProductRepository.maxTenProviders(DataBaseInitializer.DEFAULT));
                    case "-priceBetterThan" -> {
                        System.out.println("Input amount.");
                        System.out.println(ReportsProductRepository.priceBetterThan(DataBaseInitializer.DEFAULT, scanner.nextInt()));
                    }
                    case "-allProductsAmountAndSum" -> {
                        System.out.println("Input the start and end dates of the period on two different lines. Format(yyyy-mm-dd)");
                        System.out.println(ReportsProductRepository.allProductsAmountAndSum(DataBaseInitializer.DEFAULT, Date.valueOf(scanner.nextLine()), Date.valueOf(scanner.nextLine())));
                    }
                    case "-averagePriceProduct" -> {
                        System.out.println("Input the start and end dates of the period on two different lines. Format(yyyy-mm-dd)");
                        System.out.println(ReportsProductRepository.averagePriceProduct(DataBaseInitializer.DEFAULT, Date.valueOf(scanner.nextLine()), Date.valueOf(scanner.nextLine())));
                    }
                    case "-productsFromAllOrganization" -> {
                        System.out.println("Input the start and end dates of the period on two different lines. Format(yyyy-mm-dd)");
                        System.out.println(ReportsProductRepository.productsFromAllOrganization(DataBaseInitializer.DEFAULT, Date.valueOf(scanner.nextLine()), Date.valueOf(scanner.nextLine())));
                    }
                }
            }
        } catch (IllegalStateException | NoSuchElementException e) {
        }
    }
}
