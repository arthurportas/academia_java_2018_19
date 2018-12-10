package db;

public class PropertiesConnection {

    String dbuser;
    String dbpasswrod;
    String dbase;
    String jdbc;

    public  String getJdbc(){return jdbc;}

    public void setJdbc(String jdbc) {
        this.jdbc = jdbc;
    }

    public String getDbuser() {
        return dbuser;
    }

    public void setDbuser(String dbuser) {
        this.dbuser = dbuser;
    }

    public String getDbpasswrod() {
        return dbpasswrod;
    }

    public void setDbpasswrod(String dbpasswrod) {
        this.dbpasswrod = dbpasswrod;
    }

    public String getDbase() {
        return dbase;
    }

    public void setDbase(String dbase) {
        this.dbase = dbase;
    }
}
