package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    public PropertiesConnection propertiesConnection(){

        Properties prop = new Properties();
        FileInputStream file;

        PropertiesConnection propertiesConnection = new PropertiesConnection();


        try{
            // System.out.println(new File("db.properties").getAbsoluteFile());
            file = new FileInputStream("src/main/resources/db/db.properties");

            if (file != null) {
                prop.load(file);
            } else {
                throw new FileNotFoundException("property file not found in the classpath");
            }

            prop.load(file);

            propertiesConnection.setDbuser(prop.getProperty("dbuser"));
            propertiesConnection.setDbpasswrod(prop.getProperty("dbpassword"));
            propertiesConnection.setDbase(prop.getProperty("dbase"));
            propertiesConnection.setJdbc(prop.getProperty("jdbcDriver"));


        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return propertiesConnection;
    }

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

        String jdbc = propertiesConnection().getJdbc();
        Connection connection = DriverManager.getConnection(jdbc);
        return connection;
    }
}
