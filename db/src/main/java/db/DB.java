package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private static DB db;

    private DB() { };

    public static DB INSTANCE() throws ClassNotFoundException {
        if (db == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            db =  new DB();
        }
        return db;
    }

    public Connection connection() throws SQLException {

        String jdbc = "jdbc:mysql://localhost/employees?user=root&password=password";
        Connection connection = DriverManager.getConnection(jdbc);
        return connection;
    }
}
