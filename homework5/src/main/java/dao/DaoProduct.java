package dao;

import funcinterfaces.PreparedStatementAdderator;
import funcinterfaces.ResultSetMapper;
import models.Product;

import java.util.ArrayList;
import java.util.List;

public class DaoProduct implements DAO<Product> {

    @Override
    public Product get(DataBaseInitializer database, int id) {
        String sql = "SELECT * " +
                "FROM products " +
                "WHERE id = ?";

        PreparedStatementAdderator adderator = (ps) -> {
            ps.setInt(1, id);
        };

        ResultSetMapper<Product> mapper = (set) -> {
            Product product = null;
            while (set.next()) {
                product = new Product(set.getString("title"), set.getString("variety"));
            }
            return product;
        };
        return (Product) RequestExecutor.query(database, sql, adderator, mapper);
    }

    @Override
    public List<Product> all(DataBaseInitializer database) {
        String sql = "SELECT * " +
                "FROM products ";

        ResultSetMapper<List<Product>> mapper = (set) -> {
            List<Product> list = new ArrayList<>();
            while (set.next()) {
                list.add(new Product(set.getString("title"), set.getString("variety")));
            }
            return list;
        };
        return (List<Product>) RequestExecutor.query(database, sql, mapper);
    }

    @Override
    public void insert(DataBaseInitializer database, Product product) {
        String sql = "INSERT INTO products(title,variety) VALUES (?,?)";

        PreparedStatementAdderator adderator = (ps) -> {
            ps.setString(1, product.getTitle());
            ps.setString(2, product.getVariety());
        };

        RequestExecutor.update(database, sql, adderator);
    }

    @Override
    public void update(DataBaseInitializer database, int id, Product newProduct) {
        String sql = "UPDATE products\n" +
                "    SET \n" +
                "        title = ?,\n" +
                "        variety = ?\n" +
                "WHERE id = ?";

        PreparedStatementAdderator adderator = (ps) -> {
            ps.setString(1, newProduct.getTitle());
            ps.setString(2, newProduct.getVariety());
            ps.setInt(3, id);
        };

        RequestExecutor.update(database, sql, adderator);
    }

    @Override
    public void delete(DataBaseInitializer database, Product product) {
        String sql = "DELETE FROM products\n" +
                "WHERE title = ? AND variety = ?;";

        PreparedStatementAdderator adderator = (ps) -> {
            ps.setString(1, product.getTitle());
            ps.setString(2, product.getVariety());
        };

        RequestExecutor.update(database, sql, adderator);
    }
}
