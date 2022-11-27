package dao;

import funcinterfaces.DSLContextExecutor;
import funcinterfaces.DSLContextFetcher;
import funcinterfaces.ResultSetMapper;
import models.Provider;

import java.util.ArrayList;
import java.util.List;

import static generated.Tables.PROVIDERS;

public class DaoProvider implements DAO<Provider> {

    @Override
    public Provider get(DataBaseInitializer database, int id) {

        DSLContextFetcher fetcher = (context) -> {
            return context
                    .selectFrom(PROVIDERS)
                    .where(PROVIDERS.ID.equal(id))
                    .fetchResultSet();
        };

        ResultSetMapper<Provider> mapper = (set) -> {
            Provider provider = null;
            while (set.next()) {
                provider = new Provider(set.getString("title"), set.getString("TIN"), set.getString("payment_account"));
            }
            return provider;
        };
        return (Provider) RequestExecutor.query(database, fetcher, mapper);
    }

    @Override
    public List<Provider> all(DataBaseInitializer database) {

        DSLContextFetcher fetcher = (context) -> {
            return context
                    .selectFrom(PROVIDERS)
                    .fetchResultSet();
        };

        ResultSetMapper<List<Provider>> mapper = (set) -> {
            List<Provider> list = new ArrayList<>();
            while (set.next()) {
                list.add(new Provider(set.getString("title"), set.getString("TIN"), set.getString("payment_account")));
            }
            return list;
        };
        return (List<Provider>) RequestExecutor.query(database, fetcher, mapper);
    }

    @Override
    public void insert(DataBaseInitializer database, Provider provider) {
        DSLContextExecutor executor = (context) -> {
            context.insertInto(PROVIDERS, PROVIDERS.TITLE, PROVIDERS.TIN, PROVIDERS.PAYMENT_ACCOUNT)
                    .values(provider.getTitle(), provider.getTIN(), provider.getPayment_account())
                    .execute();
        };

        RequestExecutor.update(database, executor);
    }

    @Override
    public void update(DataBaseInitializer database, int id, Provider newProvider) {
        DSLContextExecutor executor = (context) -> {
            context
                    .update(PROVIDERS)
                    .set(PROVIDERS.TITLE, newProvider.getTitle())
                    .set(PROVIDERS.TIN, newProvider.getTIN())
                    .set(PROVIDERS.PAYMENT_ACCOUNT, newProvider.getPayment_account())
                    .where(PROVIDERS.ID.equal(id))
                    .execute();
        };

        RequestExecutor.update(database, executor);
    }

    @Override
    public void delete(DataBaseInitializer database, Provider provider) {
        DSLContextExecutor executor = (context) -> {
            context.delete(PROVIDERS)
                    .where(PROVIDERS.TITLE.equal(provider.getTitle())
                            .and(PROVIDERS.TIN.equal(provider.getTIN())
                                    .and(PROVIDERS.PAYMENT_ACCOUNT.equal(provider.getPayment_account()))))
                    .execute();
        };

        RequestExecutor.update(database, executor);
    }
}
