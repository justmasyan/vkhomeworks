package funcinterfaces;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedStatementAdderator {

    void addValues(PreparedStatement ps) throws SQLException;
}
