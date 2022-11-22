package dao;

import funcinterfaces.DSLContextExecutor;
import funcinterfaces.DSLContextFetcher;
import funcinterfaces.ResultSetMapper;
import models.InvoiceData;

import java.util.ArrayList;
import java.util.List;

import static generated.Tables.INVOICE_DATA;

public class DAOInvoiceData implements DAO<InvoiceData> {

    @Override
    public InvoiceData get(DataBaseInitializer database, int id) {
        DSLContextFetcher fetcher = (context) -> {
            return context
                    .selectFrom(INVOICE_DATA)
                    .where(INVOICE_DATA.ID.equal(id))
                    .fetchResultSet();
        };

        ResultSetMapper<InvoiceData> mapper = (set) -> {
            InvoiceData invoice = null;
            while (set.next()) {
                invoice = new InvoiceData(set.getInt("invoice_id"), set.getInt("product"), set.getInt("price"), set.getInt("amount"));
            }
            return invoice;
        };
        return (InvoiceData) RequestExecutor.query(database, fetcher, mapper);
    }

    @Override
    public List<InvoiceData> all(DataBaseInitializer database) {
        DSLContextFetcher fetcher = (context) -> {
            return context
                    .selectFrom(INVOICE_DATA)
                    .fetchResultSet();
        };
        ResultSetMapper<List<InvoiceData>> mapper = (set) -> {
            List<InvoiceData> list = new ArrayList<>();
            while (set.next()) {
                list.add(new InvoiceData(set.getInt("invoice_id"), set.getInt("product"), set.getInt("price"), set.getInt("amount")));
            }
            return list;
        };
        return (List<InvoiceData>) RequestExecutor.query(database, fetcher, mapper);
    }

    @Override
    public void insert(DataBaseInitializer database, InvoiceData invoice) {
        DSLContextExecutor executor = (context) -> {
            context.insertInto(INVOICE_DATA, INVOICE_DATA.INVOICE_ID, INVOICE_DATA.PRODUCT, INVOICE_DATA.PRICE, INVOICE_DATA.AMOUNT)
                    .values(invoice.getInvoiceId(), invoice.getIdProduct(), invoice.getPrice(), invoice.getAmount())
                    .execute();
        };

        RequestExecutor.update(database, executor);
    }

    @Override
    public void update(DataBaseInitializer database, int id, InvoiceData newInvoice) {

        DSLContextExecutor executor = (context) -> {
            context
                    .update(INVOICE_DATA)
                    .set(INVOICE_DATA.INVOICE_ID, newInvoice.getInvoiceId())
                    .set(INVOICE_DATA.PRODUCT, newInvoice.getIdProduct())
                    .set(INVOICE_DATA.PRICE, newInvoice.getPrice())
                    .set(INVOICE_DATA.AMOUNT, newInvoice.getAmount())
                    .where(INVOICE_DATA.ID.equal(id))
                    .execute();
        };

        RequestExecutor.update(database, executor);
    }

    @Override
    public void delete(DataBaseInitializer database, InvoiceData invoice) {
        DSLContextExecutor executor = (context) -> {
            context.delete(INVOICE_DATA)
                    .where(INVOICE_DATA.INVOICE_ID.equal(invoice.getInvoiceId())
                            .and(INVOICE_DATA.PRODUCT.equal(invoice.getIdProduct())
                                    .and(INVOICE_DATA.PRICE.equal(invoice.getPrice())
                                            .and(INVOICE_DATA.AMOUNT.equal(invoice.getAmount())))))
                    .execute();
        };

        RequestExecutor.update(database, executor);
    }
}
