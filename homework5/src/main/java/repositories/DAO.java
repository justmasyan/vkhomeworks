package repositories;

import funcinterfaces.PreparedStatementAdderator;
import funcinterfaces.ResultSetMapper;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class DAO {

    protected static void update(DataBaseInitializer database,String sql, PreparedStatementAdderator adderator){
        try (var connection = DriverManager.getConnection(database.getCONNECTION() + database.getDB_NAME(), database.getUSERNAME(), database.getPASSWORD())) {
            try (var statement = connection.prepareStatement(sql)) {
                adderator.addValues(statement);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static Map query(DataBaseInitializer database,String sql, PreparedStatementAdderator adderator, ResultSetMapper mapper){
        try (var connection = DriverManager.getConnection(database.getCONNECTION() + database.getDB_NAME(), database.getUSERNAME(), database.getPASSWORD())) {
            try (var statement = connection.prepareStatement(sql)) {
                adderator.addValues(statement);
                return mapper.get(statement.executeQuery());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected static Map query(DataBaseInitializer database,String sql,ResultSetMapper mapper){
        try (var connection = DriverManager.getConnection(database.getCONNECTION() + database.getDB_NAME(), database.getUSERNAME(), database.getPASSWORD())) {
            try (var statement = connection.createStatement()) {
                return mapper.get(statement.executeQuery(sql));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
