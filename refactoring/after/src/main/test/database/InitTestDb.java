package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InitTestDb {
    public static void initTestDb() throws Exception {
        try (Connection c = DriverManager.getConnection("jdbc:sqlite:prod.db")) {

            String createTable = "CREATE TABLE IF NOT EXISTS PRODUCT" +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " PRICE          INT     NOT NULL)";

            String clean = "DELETE FROM PRODUCT";

            Statement stmt = c.createStatement();

            stmt.executeUpdate(createTable);
            stmt.executeUpdate(clean);
            stmt.close();
        }
    }
}
