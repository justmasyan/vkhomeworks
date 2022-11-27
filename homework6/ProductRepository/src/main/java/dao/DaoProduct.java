package dao;

import funcinterfaces.DSLContextExecutor;
import funcinterfaces.DSLContextFetcher;
import funcinterfaces.ResultSetMapper;
import models.Product;

import java.util.ArrayList;
import java.util.List;

import static generated.Tables.PRODUCTS;

public class DaoProduct implements DAO<Product> {

    @Override
    public Product get(DataBaseInitializer database, int id) {

        DSLContextFetcher fetcher = (context) -> {
            return context
                    .selectFrom(PRODUCTS)
                    .where(PRODUCTS.ID.equal(id))
                    .fetchResultSet();
        };

        ResultSetMapper<Product> mapper = (set) -> {
            Product product = null;
            while (set.next()) {
                product = new Product(set.getString("title"), set.getString("variety"));
            }
            return product;
        };
        return (Product) RequestExecutor.query(database, fetcher, mapper);
    }

    @Override
    public List<Product> all(DataBaseInitializer database) {

        DSLContextFetcher fetcher = (context) -> {
            return context
                    .selectFrom(PRODUCTS)
                    .fetchResultSet();
        };

        ResultSetMapper<List<Product>> mapper = (set) -> {
            List<Product> list = new ArrayList<>();
            while (set.next()) {
                list.add(new Product(set.getString("title"), set.getString("variety")));
            }
            return list;
        };
        return (List<Product>) RequestExecutor.query(database, fetcher, mapper);
    }

    @Override
    public void insert(DataBaseInitializer database, Product product) {

        DSLContextExecutor executor = (context) -> {
            context.insertInto(PRODUCTS, PRODUCTS.TITLE, PRODUCTS.VARIETY)
                    .values(product.getTitle(), product.getVariety())
                    .execute();
        };

        RequestExecutor.update(database, executor);
    }

    @Override
    public void update(DataBaseInitializer database, int id, Product newProduct) {

        DSLContextExecutor executor = (context) -> {
            context
                    .update(PRODUCTS)
                    .set(PRODUCTS.TITLE, newProduct.getTitle())
                    .set(PRODUCTS.VARIETY, newProduct.getVariety())
                    .where(PRODUCTS.ID.equal(id))
                    .execute();
        };

        RequestExecutor.update(database, executor);
    }

    @Override
    public void delete(DataBaseInitializer database, Product product) {

        DSLContextExecutor executor = (context) -> {
            context.delete(PRODUCTS)
                    .where(PRODUCTS.TITLE.equal(product.getTitle()).and(PRODUCTS.VARIETY.equal(product.getVariety())))
                    .execute();
        };

        RequestExecutor.update(database, executor);
    }
}
