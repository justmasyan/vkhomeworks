package dao;

import funcinterfaces.DSLContextFetcher;
import funcinterfaces.ResultSetMapper;
import org.jooq.impl.DSL;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static generated.Tables.*;


public interface DAO<T> {

    T get(DataBaseInitializer database, int id);

    List<T> all(DataBaseInitializer database);

    void insert(DataBaseInitializer database, T entity);

    void update(DataBaseInitializer database, int id, T entity);

    void delete(DataBaseInitializer database, T entity);

    static Map<String, List<String>> maxTenProviders(DataBaseInitializer database) {

        DSLContextFetcher fetcher = (context) -> {
            var r = DSL.rank().over(DSL.partitionBy(INVOICE_DATA.PRODUCT).orderBy(DSL.sum(INVOICE_DATA.AMOUNT).desc())).as("r");

            var save = context
                    .select(
                            INVOICE_DATA.PRODUCT,
                            PROVIDERS.TITLE,
                            r
                    ).from(INVOICE_DATA)
                    .innerJoin(INVOICE_INFO).on(INVOICE_INFO.ID.equal(INVOICE_DATA.INVOICE_ID))
                    .innerJoin(PROVIDERS).on(PROVIDERS.ID.equal(INVOICE_INFO.PROVIDER))
                    .groupBy(INVOICE_DATA.PRODUCT, PROVIDERS.TITLE)
                    .orderBy(INVOICE_DATA.PRODUCT, r)
                    .asTable("save");

            return context
                    .select(
                            PRODUCTS.TITLE,
                            PRODUCTS.VARIETY,
                            save.field(PROVIDERS.TITLE).as("provider")
                    ).from(PRODUCTS)
                    .leftJoin(save).on(PRODUCTS.ID.equal(save.field(INVOICE_DATA.PRODUCT)))
                    .where(save.field(r).lessOrEqual(10).or(save.field(r).isNull()))
                    .orderBy(PRODUCTS.TITLE, PRODUCTS.VARIETY, save.field(r))
                    .fetchResultSet();
        };

        ResultSetMapper<Map<String, List<String>>> mapper = (set) -> {
            Map<String, List<String>> map = new HashMap<>();
            List<String> providers = new ArrayList<>();
            String key = "";
            while (set.next()) {
                String anotherKey = set.getString("title") + set.getString("variety");

                if (!key.equals(anotherKey)) {
                    if (!key.equals(""))
                        map.put(key, providers);

                    key = anotherKey;
                    providers = new ArrayList<>();
                }
                providers.add(set.getString("provider"));
            }
            if (providers.size() != 0)
                map.put(key, providers);
            return map;
        };

        return (Map<String, List<String>>) RequestExecutor.query(database, fetcher, mapper);
    }

    static Map<String, List<String>> priceBetterThan(DataBaseInitializer database, BigDecimal amount) {

        DSLContextFetcher fetcher = (context) -> {
            var sum = DSL.sum(INVOICE_DATA.AMOUNT).as("sum");
            var save = context.
                    select(
                            INVOICE_DATA.PRODUCT,
                            PROVIDERS.TITLE,
                            sum
                    ).from(INVOICE_DATA)
                    .innerJoin(INVOICE_INFO).on(INVOICE_INFO.ID.equal(INVOICE_DATA.INVOICE_ID))
                    .innerJoin(PROVIDERS).on(PROVIDERS.ID.equal(INVOICE_INFO.PROVIDER))
                    .groupBy(INVOICE_DATA.PRODUCT, PROVIDERS.TITLE)
                    .orderBy(INVOICE_DATA.PRODUCT, sum.desc())
                    .asTable("sorted");

            return context
                    .select(
                            save.field(PROVIDERS.TITLE).as("title_provider"),
                            PRODUCTS.TITLE.as("title_product"),
                            PRODUCTS.VARIETY.as("variety")
                    ).from(PRODUCTS)
                    .leftJoin(save)
                    .on(save.field(INVOICE_DATA.PRODUCT).equal(PRODUCTS.ID))
                    .where(save.field(sum).greaterThan(amount))
                    .orderBy(save.field(PROVIDERS.TITLE).desc())
                    .fetchResultSet();

        };

        ResultSetMapper<Map<String, List<String>>> mapper = (set) -> {
            Map<String, List<String>> map = new HashMap<>();
            List<String> products = new ArrayList<>();
            String key = "";
            while (set.next()) {
                String anotherKey = set.getString("title_provider");

                if (!key.equals(anotherKey)) {
                    if (!key.equals(""))
                        map.put(key, products);

                    key = anotherKey;
                    products = new ArrayList<>();
                }
                products.add(set.getString("title_product") + set.getString("variety"));
            }
            if (products.size() != 0)
                map.put(key, products);
            return map;
        };

        return (Map<String, List<String>>) RequestExecutor.query(database, fetcher, mapper);
    }

    static Map<String, List<Integer>> allProductsAmountAndSum(DataBaseInitializer database, LocalDate startPeriod, LocalDate finishPeriod) {

        DSLContextFetcher fetcher = (context) -> {
            var total_amount = DSL.sum(INVOICE_DATA.AMOUNT).as("total_amount");
            var total_sum = DSL.sum(INVOICE_DATA.AMOUNT.multiply(INVOICE_DATA.PRICE)).as("total_sum");

            var save = context
                    .select(
                            INVOICE_DATA.PRODUCT,
                            total_amount,
                            total_sum
                    ).from(INVOICE_DATA)
                    .innerJoin(INVOICE_INFO).on(INVOICE_INFO.ID.equal(INVOICE_DATA.INVOICE_ID))
                    .where(INVOICE_INFO.DATE.greaterOrEqual(startPeriod).and(INVOICE_INFO.DATE.lessOrEqual(finishPeriod)))
                    .groupBy(INVOICE_DATA.PRODUCT)
                    .asTable("save");

            return context
                    .select(
                            PRODUCTS.TITLE,
                            PRODUCTS.VARIETY,
                            save.field(total_amount),
                            save.field(total_sum)
                    ).from(PRODUCTS)
                    .leftJoin(save).on(PRODUCTS.ID.equal(save.field(INVOICE_DATA.PRODUCT)))
                    .orderBy(PRODUCTS.TITLE, PRODUCTS.VARIETY)
                    .fetchResultSet();

        };

        ResultSetMapper<Map<String, List<Integer>>> mapper = (set) -> {
            Map<String, List<Integer>> map = new HashMap<>();
            List<Integer> totalAmountAndSum;

            while (set.next()) {
                String key = set.getString("title") + set.getString("variety");
                totalAmountAndSum = new ArrayList<>();
                totalAmountAndSum.add(set.getInt("total_amount"));
                totalAmountAndSum.add(set.getInt("total_sum"));
                map.put(key, totalAmountAndSum);
            }

            return map;
        };

        return (Map<String, List<Integer>>) RequestExecutor.query(database, fetcher, mapper);
    }

    static Map<String, BigDecimal> averagePriceProduct(DataBaseInitializer database, LocalDate startPeriod, LocalDate finishPeriod) {

        DSLContextFetcher fetcher = (context) -> {
            var avgPrice = DSL.avg(INVOICE_DATA.PRICE).as("avg_price");
            var save = context
                    .select(
                            INVOICE_DATA.PRODUCT,
                            avgPrice
                    ).from(INVOICE_INFO)
                    .innerJoin(INVOICE_DATA).on(INVOICE_INFO.ID.equal(INVOICE_DATA.INVOICE_ID))
                    .where(INVOICE_INFO.DATE.greaterOrEqual(startPeriod).and(INVOICE_INFO.DATE.lessOrEqual(finishPeriod)))
                    .groupBy(INVOICE_DATA.PRODUCT)
                    .asTable("save");
            return context
                    .select(
                            PRODUCTS.TITLE,
                            PRODUCTS.VARIETY,
                            save.field(avgPrice)
                    ).from(PRODUCTS)
                    .leftJoin(save).on(PRODUCTS.ID.equal(save.field(INVOICE_DATA.PRODUCT)))
                    .orderBy(save.field(avgPrice))
                    .fetchResultSet();
        };

        ResultSetMapper<Map<String, BigDecimal>> mapper = (set) -> {
            Map<String, BigDecimal> map = new HashMap<>();

            while (set.next()) {
                String key = set.getString("title") + set.getString("variety");
                BigDecimal averagePrice = set.getBigDecimal("avg_price");
                map.put(key, averagePrice);
            }

            return map;
        };

        return (Map<String, BigDecimal>) RequestExecutor.query(database, fetcher, mapper);
    }

    static Map<String, List<String>> productsFromAllOrganization(DataBaseInitializer database, LocalDate startPeriod, LocalDate finishPeriod) {

        DSLContextFetcher fetcher = (context) -> {
            var save = context
                    .select(
                            INVOICE_INFO.PROVIDER,
                            PRODUCTS.TITLE.as("title_product"),
                            PRODUCTS.VARIETY
                    ).from(INVOICE_INFO)
                    .innerJoin(INVOICE_DATA).on(INVOICE_DATA.INVOICE_ID.equal(INVOICE_INFO.ID))
                    .innerJoin(PRODUCTS).on(INVOICE_DATA.PRODUCT.equal(PRODUCTS.ID))
                    .where(INVOICE_INFO.DATE.greaterOrEqual(startPeriod).and(INVOICE_INFO.DATE.lessOrEqual(finishPeriod)))
                    .groupBy(INVOICE_INFO.PROVIDER, PRODUCTS.TITLE, PRODUCTS.VARIETY)
                    .asTable("save");
            return context
                    .select(
                            PROVIDERS.TITLE.as("provider_name"),
                            save.field("title_product"),
                            save.field(PRODUCTS.VARIETY)
                    ).from(PROVIDERS)
                    .leftJoin(save).on(PROVIDERS.ID.equal(save.field(INVOICE_INFO.PROVIDER)))
                    .orderBy(PROVIDERS.TITLE.desc())
                    .fetchResultSet();
        };

        ResultSetMapper<Map<String, List<String>>> mapper = (set) -> {
            Map<String, List<String>> map = new HashMap<>();
            List<String> products = new ArrayList<>();
            String key = "";
            while (set.next()) {
                String anotherKey = set.getString("provider_name");

                if (!key.equals(anotherKey)) {
                    if (!key.equals(""))
                        map.put(key, products);

                    key = anotherKey;
                    products = new ArrayList<>();
                }
                products.add(set.getString("title_product") + set.getString("variety"));
            }
            if (products.size() != 0)
                map.put(key, products);
            return map;
        };

        return (Map<String, List<String>>) RequestExecutor.query(database, fetcher, mapper);
    }

}
