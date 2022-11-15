package repositories;

import org.flywaydb.core.Flyway;

public class DataBaseInitializer {
    private final String CONNECTION;
    private final String DB_NAME;
    private final String USERNAME;
    private final String PASSWORD;

    public DataBaseInitializer(String host,String port,String dbName,String login,String password){
        CONNECTION = "jdbc:postgresql://" + host + ":" + port + "/";
        DB_NAME = dbName;
        USERNAME = login;
        PASSWORD = password;
    }

    public final static DataBaseInitializer DEFAULT = new DataBaseInitializer(
            "localhost",
            "5432",
            "ReportProducts",
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

    public static void initDefaultDatabase(){
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
    public static void initTestDatabase(){
        final Flyway flyway = Flyway
                .configure()
                .dataSource(DataBaseInitializer.DEFAULT.getCONNECTION() + DataBaseInitializer.DEFAULT.getDB_NAME() + "Test", DataBaseInitializer.DEFAULT.getUSERNAME(), DataBaseInitializer.DEFAULT.getPASSWORD())
                .locations("db")
                .cleanDisabled(false)
                .load();
        flyway.clean();
        flyway.migrate();
        System.out.println("Migrations applied successfully");
    }
}
