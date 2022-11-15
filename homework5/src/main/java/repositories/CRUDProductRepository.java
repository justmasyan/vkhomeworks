package repositories;

import funcinterfaces.PreparedStatementAdderator;
import funcinterfaces.ResultSetMapper;
import models.Invoice;
import models.Product;
import models.Provider;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CRUDProductRepository {

    public static void addProduct(DataBaseInitializer dataBase, Product product) {
        String sql = "INSERT INTO products(title,variety) VALUES (?,?)";

        PreparedStatementAdderator adderator = (ps) -> {
            ps.setString(1, product.getTitle());
            ps.setString(2, product.getVariety());
        };

        DAO.update(dataBase, sql, adderator);
    }

    public static void addProvider(DataBaseInitializer dataBase, Provider provider) {
        String sql = "INSERT INTO providers(title,TIN,payment_account) VALUES (?,?,?)";
        PreparedStatementAdderator adderator = (ps) -> {
            ps.setString(1, provider.getTitle());
            ps.setString(2, provider.getTIN());
            ps.setString(3, provider.getPayment_account());
        };
        DAO.update(dataBase, sql, adderator);
    }

    public static void addInvoice(DataBaseInitializer database, Invoice invoice) {
        String sql = "INSERT INTO invoice_info(date,provider) VALUES (?,?)";
        PreparedStatementAdderator adderator = (ps) -> {
            ps.setDate(1, invoice.getDate());
            ps.setString(2, invoice.getProvider());
        };
        DAO.update(database, sql, adderator);

        sql = "SELECT MAX(id) FROM invoice_info";
        ResultSetMapper<String, Integer> mapper = (set) -> {
            Map<String, Integer> map = new HashMap<>();
            int count = 1;
            while (set.next()) {
                map.put(Integer.toString(count), set.getInt("max"));
            }
            return map;
        };

        int idInvoice = (Integer) DAO.query(database, sql, mapper).get("1");

        sql = "INSERT INTO invoice_data(invoice_id,product,price,amount) VALUES(?,?,?,?)";
        int count = invoice.getIdProducts().length;

        for (int i = 0; i < count; i++) {
            int id_product = invoice.getIdProducts()[i];
            int price = invoice.getPrices()[i];
            int amount = invoice.getAmount()[i];

            adderator = (ps) -> {
                ps.setInt(1, idInvoice);
                ps.setInt(2, id_product);
                ps.setInt(3, price);
                ps.setInt(4, amount);
            };

            DAO.update(database, sql, adderator);
        }
    }

    public static Map<Integer, Provider> getProviderByTitle(DataBaseInitializer dataBase, String title) {
        String sql = "SELECT * " +
                "FROM providers " +
                "WHERE title = ?";

        PreparedStatementAdderator adderator = (ps) -> {
            ps.setString(1, title);
        };

        ResultSetMapper<Integer, Provider> mapper = (set) -> {
            Map<Integer, Provider> map = new HashMap<>();
            int count = 1;
            while (set.next()) {
                Provider provider = new Provider(set.getString("title"),
                        set.getString("TIN"),
                        set.getString("payment_account"));
                map.put(count, provider);
                count++;
            }
            return map;
        };
        return DAO.query(dataBase, sql, adderator, mapper);
    }

    public static Map<Integer, Product> getProductById(DataBaseInitializer dataBase, int id) {
        String sql = "SELECT * " +
                "FROM products " +
                "WHERE id = ?";

        PreparedStatementAdderator adderator = (ps) -> {
            ps.setInt(1, id);
        };

        ResultSetMapper<Integer, Product> mapper = (set) -> {
            Map<Integer, Product> map = new HashMap<>();
            while (set.next()) {
                map.put(set.getInt("id"), new Product(set.getString("title"), set.getString("variety")));
            }
            return map;
        };
        return DAO.query(dataBase, sql, adderator, mapper);
    }

    public static Map<Integer, Invoice> getInvoiceById(DataBaseInitializer dataBase, int id) {
        String sql = "SELECT date,provider,product,price,amount\n" +
                "FROM invoice_info\n" +
                "INNER JOIN invoice_data ON invoice_info.id = invoice_id\n" +
                "WHERE invoice_id = ?";

        PreparedStatementAdderator adderator = (ps) -> {
            ps.setInt(1, id);
        };

        ResultSetMapper<Integer, Invoice> mapper = (set) -> {
            Map<Integer,Invoice> map = new HashMap<>();

            if(set.next()) {
                List<Integer> productsId = new ArrayList<>();
                List<Integer> prices = new ArrayList<>();
                List<Integer> amount = new ArrayList<>();

                Date date = set.getDate("date");
                String provider = set.getString("provider");

                do {
                    productsId.add(set.getInt("product"));
                    prices.add(set.getInt("price"));
                    amount.add(set.getInt("amount"));
                } while (set.next());

                map.put(id,new Invoice(date,
                        provider,
                        productsId.stream().mapToInt(i -> i).toArray(),
                        prices.stream().mapToInt(i -> i).toArray(),
                        amount.stream().mapToInt(i -> i).toArray()
                ));
            }

            return map;
        };
        return DAO.query(dataBase,sql,adderator,mapper);
    }

}
