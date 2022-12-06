package repositoryies.funcinterfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetMapper<T> {
    T get(ResultSet set) throws SQLException;
}
