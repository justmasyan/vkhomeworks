package dao;

import funcinterfaces.DSLContextExecutor;
import funcinterfaces.DSLContextFetcher;
import funcinterfaces.ResultSetMapper;
import models.InvoiceInfo;

import java.util.ArrayList;
import java.util.List;

import static generated.Tables.INVOICE_INFO;

public class DAOInvoiceInfo implements DAO<InvoiceInfo> {

    @Override
    public InvoiceInfo get(DataBaseInitializer database, int id) {
        DSLContextFetcher fetcher = (context) -> {
            return context
                    .selectFrom(INVOICE_INFO)
                    .where(INVOICE_INFO.ID.equal(id))
                    .fetchResultSet();
        };

        ResultSetMapper<InvoiceInfo> mapper = (set) -> {
            InvoiceInfo invoice = null;
            while (set.next()) {
                invoice = new InvoiceInfo(set.getDate("date").toLocalDate(), set.getInt("provider"));
            }
            return invoice;
        };
        return (InvoiceInfo) RequestExecutor.query(database, fetcher, mapper);
    }

    @Override
    public List<InvoiceInfo> all(DataBaseInitializer database) {
        DSLContextFetcher fetcher = (context) -> {
            return context
                    .selectFrom(INVOICE_INFO)
                    .fetchResultSet();
        };

        ResultSetMapper<List<InvoiceInfo>> mapper = (set) -> {
            List<InvoiceInfo> list = new ArrayList<>();
            while (set.next()) {
                list.add(new InvoiceInfo(set.getDate("date").toLocalDate(), set.getInt("provider")));
            }
            return list;
        };
        return (List<InvoiceInfo>) RequestExecutor.query(database, fetcher, mapper);
    }

    @Override
    public void insert(DataBaseInitializer database, InvoiceInfo invoice) {
        DSLContextExecutor executor = (context) -> {
            context.insertInto(INVOICE_INFO, INVOICE_INFO.DATE, INVOICE_INFO.PROVIDER)
                    .values(invoice.getDate(), invoice.getProvider())
                    .execute();
        };

        RequestExecutor.update(database, executor);
    }

    @Override
    public void update(DataBaseInitializer database, int id, InvoiceInfo newInvoice) {
        DSLContextExecutor executor = (context) -> {
            context
                    .update(INVOICE_INFO)
                    .set(INVOICE_INFO.DATE, newInvoice.getDate())
                    .set(INVOICE_INFO.PROVIDER, newInvoice.getProvider())
                    .where(INVOICE_INFO.ID.equal(id))
                    .execute();
        };

        RequestExecutor.update(database, executor);
    }

    @Override
    public void delete(DataBaseInitializer database, InvoiceInfo invoice) {
        DSLContextExecutor executor = (context) -> {
            context.delete(INVOICE_INFO)
                    .where(INVOICE_INFO.DATE.equal(invoice.getDate())
                            .and(INVOICE_INFO.PROVIDER.equal(invoice.getProvider())))
                    .execute();
        };

        RequestExecutor.update(database, executor);
    }
}
