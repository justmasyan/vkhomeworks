package dao;

import funcinterfaces.PreparedStatementAdderator;
import funcinterfaces.ResultSetMapper;
import models.InvoiceInfo;

import java.util.ArrayList;
import java.util.List;

public class DAOInvoiceInfo implements DAO<InvoiceInfo> {

    @Override
    public InvoiceInfo get(DataBaseInitializer database, int id) {
        String sql = "SELECT * " +
                "FROM invoice_info " +
                "WHERE id = ?";

        PreparedStatementAdderator adderator = (ps) -> {
            ps.setInt(1, id);
        };

        ResultSetMapper<InvoiceInfo> mapper = (set) -> {
            InvoiceInfo invoice = null;
            while (set.next()) {
                invoice = new InvoiceInfo(set.getDate("date"), set.getInt("provider"));
            }
            return invoice;
        };
        return (InvoiceInfo) RequestExecutor.query(database, sql, adderator, mapper);
    }

    @Override
    public List<InvoiceInfo> all(DataBaseInitializer database) {
        String sql = "SELECT * " +
                "FROM invoice_info ";

        ResultSetMapper<List<InvoiceInfo>> mapper = (set) -> {
            List<InvoiceInfo> list = new ArrayList<>();
            while (set.next()) {
                list.add(new InvoiceInfo(set.getDate("date"), set.getInt("provider")));
            }
            return list;
        };
        return (List<InvoiceInfo>) RequestExecutor.query(database, sql, mapper);
    }

    @Override
    public void insert(DataBaseInitializer database, InvoiceInfo invoice) {
        String sql = "INSERT INTO invoice_info(date,provider) VALUES (?,?)";

        PreparedStatementAdderator adderator = (ps) -> {
            ps.setDate(1, invoice.getDate());
            ps.setInt(2, invoice.getProvider());
        };

        RequestExecutor.update(database, sql, adderator);
    }

    @Override
    public void update(DataBaseInitializer database, int id, InvoiceInfo newInvoice) {
        String sql = "UPDATE invoice_info\n" +
                "    SET \n" +
                "        date = ?,\n" +
                "        provider = ?\n" +
                "WHERE id = ?";

        PreparedStatementAdderator adderator = (ps) -> {
            ps.setDate(1, newInvoice.getDate());
            ps.setInt(2, newInvoice.getProvider());
            ps.setInt(3, id);
        };

        RequestExecutor.update(database, sql, adderator);
    }

    @Override
    public void delete(DataBaseInitializer database, InvoiceInfo invoice) {
        String sql = "DELETE FROM invoice_info\n" +
                "WHERE date = ? AND provider = ?;";

        PreparedStatementAdderator adderator = (ps) -> {
            ps.setDate(1, invoice.getDate());
            ps.setInt(2, invoice.getProvider());
        };

        RequestExecutor.update(database, sql, adderator);
    }
}
