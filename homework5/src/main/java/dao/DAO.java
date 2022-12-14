package dao;

import funcinterfaces.PreparedStatementAdderator;
import funcinterfaces.ResultSetMapper;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DAO<T> {

    T get(DataBaseInitializer database, int id);

    List<T> all(DataBaseInitializer database);

    void insert(DataBaseInitializer database, T entity);

    void update(DataBaseInitializer database, int id, T entity);

    void delete(DataBaseInitializer database, T entity);

    static Map<String, List<String>> maxTenProviders(DataBaseInitializer database) {

        String sql = "SELECT title,variety,title_provider FROM products\n" +
                "LEFT JOIN (\n" +
                "    SELECT product,providers.title as title_provider,RANK() OVER(PARTITION BY product ORDER BY SUM(AMOUNT) DESC) AS r\n" +
                "    FROM invoice_data\n" +
                "    INNER JOIN invoice_info ON invoice_id = invoice_info.id\n" +
                "    INNER JOIN providers ON providers.id = provider\n" +
                "    GROUP BY product,providers.title\n" +
                "    ORDER BY product, r\n" +
                "    ) as sorted\n" +
                "ON products.id = product\n" +
                "WHERE r <= 10 OR r is null\n" +
                "ORDER by title,variety,r";

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
                providers.add(set.getString("title_provider"));
            }
            if (providers.size() != 0)
                map.put(key, providers);
            return map;
        };

        return (Map<String, List<String>>) RequestExecutor.query(database, sql, mapper);
    }

    static Map<String, List<String>> priceBetterThan(DataBaseInitializer database, int amount) {
        String sql = "SELECT title_provider,title,variety FROM products\n" +
                "LEFT JOIN (\n" +
                "    SELECT product,providers.title as title_provider,SUM(amount) as amount\n" +
                "    FROM invoice_data\n" +
                "    INNER JOIN invoice_info ON invoice_id = invoice_info.id\n" +
                "    INNER JOIN providers ON providers.id = provider\n" +
                "    GROUP BY product,providers.title\n" +
                "    ORDER BY product, amount DESC\n" +
                "    ) as sorted\n" +
                "ON products.id = product\n" +
                "WHERE amount > ?\n" +
                "ORDER BY title_provider DESC";

        PreparedStatementAdderator adderator = (ps) -> ps.setInt(1, amount);
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
                products.add(set.getString("title") + set.getString("variety"));
            }
            if (products.size() != 0)
                map.put(key, products);
            return map;
        };

        return (Map<String, List<String>>) RequestExecutor.query(database, sql, adderator, mapper);
    }

    static Map<String, List<Integer>> allProductsAmountAndSum(DataBaseInitializer database, Date finishPeriod, Date startPeriod) {
        String sql = "SELECT title,variety,total_amount,total_sum FROM products\n" +
                "LEFT JOIN (\n" +
                "    SELECT product,SUM(amount) as total_amount, SUM(price * amount) as total_sum  FROM invoice_data\n" +
                "    INNER JOIN invoice_info ON invoice_id = invoice_info.id\n" +
                "    WHERE date >= ? AND date <= ?\n" +
                "    GROUP BY product\n" +
                ") as grouped\n" +
                "ON products.id = product\n" +
                "ORDER BY title,variety DESC;";

        PreparedStatementAdderator adderator = (ps) -> {
            ps.setDate(1, startPeriod);
            ps.setDate(2, finishPeriod);
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

        return (Map<String, List<Integer>>) RequestExecutor.query(database, sql, adderator, mapper);
    }

    static Map<String, BigDecimal> averagePriceProduct(DataBaseInitializer database, Date finishPeriod, Date startPeriod) {
        String sql = "SELECT title,variety,avg_price FROM products\n" +
                "LEFT JOIN(\n" +
                "    SELECT product,AVG(price) as avg_price\n" +
                "    FROM invoice_info\n" +
                "    INNER JOIN invoice_data ON invoice_info.id = invoice_id\n" +
                "    WHERE date >= ? AND date <= ?\n" +
                "    GROUP BY product\n" +
                "        )as sorted\n" +
                "ON products.id = product\n" +
                "ORDER BY avg_price";

        PreparedStatementAdderator adderator = (ps) -> {
            ps.setDate(1, startPeriod);
            ps.setDate(2, finishPeriod);
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

        return (Map<String, BigDecimal>) RequestExecutor.query(database, sql, adderator, mapper);
    }

    static Map<String, List<String>> productsFromAllOrganization(DataBaseInitializer database, Date startPeriod, Date finishPeriod) {
        String sql = "SELECT providers.title as provider_name, sorted.title AS title, variety FROM providers\n" +
                "LEFT JOIN (\n" +
                "        SELECT provider,products.title,variety FROM invoice_info\n" +
                "        INNER JOIN invoice_data ON invoice_id = invoice_info.id\n" +
                "        INNER JOIN products ON product = products.id\n" +
                "        WHERE date >= ? AND date <= ?\n" +
                "        GROUP BY provider,products,title,variety\n" +
                "    ) AS sorted\n" +
                "ON providers.id = provider\n" +
                "ORDER BY provider_name DESC";

        PreparedStatementAdderator adderator = (ps) -> {
            ps.setDate(1, startPeriod);
            ps.setDate(2, finishPeriod);
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
                products.add(set.getString("title") + set.getString("variety"));
            }
            if (products.size() != 0)
                map.put(key, products);
            return map;
        };

        return (Map<String, List<String>>) RequestExecutor.query(database, sql, adderator, mapper);
    }

}
