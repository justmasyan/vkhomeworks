package funcinterfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultSetMapper<T> {

    T get(ResultSet set) throws SQLException;
}
