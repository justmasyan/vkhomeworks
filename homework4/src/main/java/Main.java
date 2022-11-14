import org.flywaydb.core.Flyway;

public class Main {
    public static final String CONNECTION = "jdbc:postgresql://localhost:5432/";
    public static final String DB_NAME = "OnlineLesson";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "postgres";

    public static void main(String[] args) {
        final Flyway flyway = Flyway
                .configure()
                .dataSource(CONNECTION + DB_NAME, USERNAME, PASSWORD)
                .locations("db")
//                .cleanDisabled(false)
                .load();
//        flyway.clean();
        flyway.migrate();
        System.out.println("Migrations applied successfully");
    }
}
