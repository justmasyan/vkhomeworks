package funcinterfaces;

import org.jooq.DSLContext;

import java.sql.ResultSet;

@FunctionalInterface
public interface DSLContextFetcher {

    ResultSet addValues(DSLContext context);
}
