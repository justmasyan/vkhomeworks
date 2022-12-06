package repositoryies;

import repositoryies.funcinterfaces.DSLContextExecutor;
import repositoryies.funcinterfaces.DSLContextFetcher;
import repositoryies.funcinterfaces.ResultSetMapper;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestExecutor {

    protected static void update(DataBaseInitializer database, DSLContextExecutor executor) {
        try (var connection = DriverManager.getConnection(database.getCONNECTION() + database.getDB_NAME(), database.getUSERNAME(), database.getPASSWORD())) {
            final DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);
            executor.addValues(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static Object query(DataBaseInitializer database, DSLContextFetcher fetcher, ResultSetMapper mapper) {
        try (var connection = DriverManager.getConnection(database.getCONNECTION() + database.getDB_NAME(), database.getUSERNAME(), database.getPASSWORD())) {
            final DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);
            ResultSet set = fetcher.addValues(context);
            return mapper.get(set);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
