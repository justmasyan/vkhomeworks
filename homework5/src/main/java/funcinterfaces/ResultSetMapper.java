package funcinterfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@FunctionalInterface
public interface ResultSetMapper<T,V> {
    Map<T,V> get(ResultSet set) throws SQLException;
}
