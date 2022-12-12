package repositoryies;

import org.flywaydb.core.Flyway;

public class DataBaseInitializer {
    private final String CONNECTION;
    private final String DB_NAME;
    private final String USERNAME;
    private final String PASSWORD;

    public DataBaseInitializer(String connection, String dbName, String login, String password) {
        CONNECTION = connection;
        DB_NAME = dbName;
        USERNAME = login;
        PASSWORD = password;
    }

    public final static DataBaseInitializer DEFAULT = new DataBaseInitializer(
            "jdbc:postgresql://localhost:5432/",
            "http_server_jetty",
            "postgres",
            "postgres"
    );

    public String getCONNECTION() {
        return CONNECTION;
    }

    public String getDB_NAME() {
        return DB_NAME;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public static void initDefaultDatabase() {
        final Flyway flyway = Flyway
                .configure()
                .dataSource(DataBaseInitializer.DEFAULT.getCONNECTION() + DataBaseInitializer.DEFAULT.getDB_NAME(), DataBaseInitializer.DEFAULT.getUSERNAME(), DataBaseInitializer.DEFAULT.getPASSWORD())
                .locations("db")
                .cleanDisabled(false)
                .load();
        flyway.clean();
        flyway.migrate();
        System.out.println("Migrations applied successfully");
    }
}
