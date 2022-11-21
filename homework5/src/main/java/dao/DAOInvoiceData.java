package dao;

import funcinterfaces.PreparedStatementAdderator;
import funcinterfaces.ResultSetMapper;
import models.InvoiceData;

import java.util.ArrayList;
import java.util.List;

public class DAOInvoiceData implements DAO<InvoiceData> {

    @Override
    public InvoiceData get(DataBaseInitializer database, int id) {
        String sql = "SELECT * " +
                "FROM invoice_data " +
                "WHERE id = ?";

        PreparedStatementAdderator adderator = (ps) -> {
            ps.setInt(1, id);
        };

        ResultSetMapper<InvoiceData> mapper = (set) -> {
            InvoiceData invoice = null;
            while (set.next()) {
                invoice = new InvoiceData(set.getInt("invoice_id"), set.getInt("product"), set.getInt("price"), set.getInt("amount"));
            }
            return invoice;
        };
        return (InvoiceData) RequestExecutor.query(database, sql, adderator, mapper);
    }

    @Override
    public List<InvoiceData> all(DataBaseInitializer database) {
        String sql = "SELECT * " +
                "FROM invoice_data ";

        ResultSetMapper<List<InvoiceData>> mapper = (set) -> {
            List<InvoiceData> list = new ArrayList<>();
            while (set.next()) {
                list.add(new InvoiceData(set.getInt("invoice_id"), set.getInt("product"), set.getInt("price"), set.getInt("amount")));
            }
            return list;
        };
        return (List<InvoiceData>) RequestExecutor.query(database, sql, mapper);
    }

    @Override
    public void insert(DataBaseInitializer database, InvoiceData invoice) {
        String sql = "INSERT INTO invoice_data(invoice_id,product,price,amount) VALUES (?,?,?,?)";

        PreparedStatementAdderator adderator = (ps) -> {
            ps.setInt(1, invoice.getInvoiceId());
            ps.setInt(2, invoice.getIdProduct());
            ps.setInt(3, invoice.getPrice());
            ps.setInt(4, invoice.getAmount());
        };

        RequestExecutor.update(database, sql, adderator);
    }

    @Override
    public void update(DataBaseInitializer database, int id, InvoiceData newInvoice) {
        String sql = "UPDATE invoice_data\n" +
                "    SET \n" +
                "        invoice_id = ?,\n" +
                "        product = ?,\n" +
                "        price = ?,\n" +
                "        amount = ?\n" +
                "WHERE id = ?";

        PreparedStatementAdderator adderator = (ps) -> {
            ps.setInt(1, newInvoice.getInvoiceId());
            ps.setInt(2, newInvoice.getIdProduct());
            ps.setInt(3, newInvoice.getPrice());
            ps.setInt(4, newInvoice.getAmount());
            ps.setInt(5, id);
        };

        RequestExecutor.update(database, sql, adderator);
    }

    @Override
    public void delete(DataBaseInitializer database, InvoiceData invoice) {
        String sql = "DELETE FROM invoice_data\n" +
                "WHERE invoice_id = ? AND product = ? AND price = ? AND amount = ?";

        PreparedStatementAdderator adderator = (ps) -> {
            ps.setInt(1, invoice.getInvoiceId());
            ps.setInt(2, invoice.getIdProduct());
            ps.setInt(3, invoice.getPrice());
            ps.setInt(4, invoice.getAmount());
        };

        RequestExecutor.update(database, sql, adderator);
    }
}
