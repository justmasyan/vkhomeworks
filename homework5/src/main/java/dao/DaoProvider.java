package dao;

import funcinterfaces.PreparedStatementAdderator;
import funcinterfaces.ResultSetMapper;
import models.Provider;

import java.util.ArrayList;
import java.util.List;

public class DaoProvider implements DAO<Provider> {

    @Override
    public Provider get(DataBaseInitializer database, int id) {
        String sql = "SELECT * " +
                "FROM providers " +
                "WHERE id = ?";

        PreparedStatementAdderator adderator = (ps) -> {
            ps.setInt(1, id);
        };

        ResultSetMapper<Provider> mapper = (set) -> {
            Provider provider = null;
            while (set.next()) {
                provider = new Provider(set.getString("title"), set.getString("TIN"), set.getString("payment_account"));
            }
            return provider;
        };
        return (Provider) RequestExecutor.query(database, sql, adderator, mapper);
    }

    @Override
    public List<Provider> all(DataBaseInitializer database) {
        String sql = "SELECT * " +
                "FROM providers ";

        ResultSetMapper<List<Provider>> mapper = (set) -> {
            List<Provider> list = new ArrayList<>();
            while (set.next()) {
                list.add(new Provider(set.getString("title"), set.getString("TIN"), set.getString("payment_account")));
            }
            return list;
        };
        return (List<Provider>) RequestExecutor.query(database, sql, mapper);
    }

    @Override
    public void insert(DataBaseInitializer database, Provider provider) {
        String sql = "INSERT INTO providers(title,TIN,payment_account) VALUES (?,?,?)";

        PreparedStatementAdderator adderator = (ps) -> {
            ps.setString(1, provider.getTitle());
            ps.setString(2, provider.getTIN());
            ps.setString(3, provider.getPayment_account());
        };

        RequestExecutor.update(database, sql, adderator);
    }

    @Override
    public void update(DataBaseInitializer database, int id, Provider newProvider) {
        String sql = "UPDATE providers\n" +
                "    SET \n" +
                "        title = ?,\n" +
                "        TIN = ?,\n" +
                "        payment_account = ?\n" +
                "WHERE id = ?";

        PreparedStatementAdderator adderator = (ps) -> {
            ps.setString(1, newProvider.getTitle());
            ps.setString(2, newProvider.getTIN());
            ps.setString(3, newProvider.getPayment_account());
            ps.setInt(4, id);
        };

        RequestExecutor.update(database, sql, adderator);
    }

    @Override
    public void delete(DataBaseInitializer database, Provider provider) {
        String sql = "DELETE FROM providers\n" +
                "WHERE title = ? AND TIN = ?  AND payment_account = ?";

        PreparedStatementAdderator adderator = (ps) -> {
            ps.setString(1, provider.getTitle());
            ps.setString(2, provider.getTIN());
            ps.setString(3, provider.getPayment_account());
        };

        RequestExecutor.update(database, sql, adderator);
    }
}
