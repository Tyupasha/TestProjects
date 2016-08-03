package web.tasker.tyupa.app.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Файл конфигурации выглядит след образом:
 * driver url database user ...
 * данные забираются из файла и кладутся в массив
 * КАК НАДО СДЕЛАТЬ ДЛЯ УНИВЕРСАЛЬНОСТИ:
 * Файл кофигурации:
 * driver: driver
 * URL: url
 * ...
 * при чтении файла создаётся HashMap, все значения, которые находятся до ":" кладутся в key
 * после ":" кладутся в values по соответствующим ключам
 * Для данной реализации нужно написать парсер
 */

public class DataBaseConnector {
    private String driver;
    private String url;
    private String dataBase;
    private String user;
    private String password;
    private String fileConfigurationPath;

    private static Logger log = Logger.getLogger(DataBaseConnector.class.getName());

    public DataBaseConnector(String fileConfigurationPath) {
        HashMap<String,String> configHashMap = FileWorker.read(fileConfigurationPath);

        this.fileConfigurationPath = fileConfigurationPath;
        this.driver = configHashMap.get("Driver");
        this.url = configHashMap.get("Url");
        this.dataBase = configHashMap.get("DataBase");
        this.user = configHashMap.get("User");
        this.password = configHashMap.get("Password");
    }

    protected String getDriver() {
        return this.driver;
    }
    protected String getUrl() {
        return this.url;
    }
    protected String getDataBase() {
        return this.dataBase;
    }
    protected String getUser() {
        return this.user;
    }
    protected String getPassword() {
        return this.password;
    }


    protected void setDriver(String driver) {
        this.driver = driver;
    }
    protected void setUrl(String url) {
        this.url = url;
    }
    protected void setDataBase(String dataBase) {
        this.dataBase = dataBase;
    }
    protected void setUser(String user) {
        this.user = user;
    }
    protected void setPassword(String password) {
        this.password = password;
    }

    public Connection getDataBaseConnection() {
        Connection dataBaseConnection = null;
        try {
            Class.forName(this.getDriver());
        } catch (ClassNotFoundException classNotFoundException) {
            log.log(Level.SEVERE, "ClassNotFoundException: ", classNotFoundException);
        }
        try {
            dataBaseConnection = DriverManager.getConnection(this.getUrl()+this.getDataBase(),this.getUser(), this.getPassword());
            return dataBaseConnection;
        } catch (SQLException sqlException) {
            log.log(Level.SEVERE, "SQLException: ", sqlException);
        }
        return dataBaseConnection;
    }


    public void closeDataBaseConnection(Connection connection, Statement statement) {
        try {
            if(statement!=null) {
                statement.close();
            }
        } catch (SQLException sqlException) {
            log.log(Level.SEVERE, "sqlException with statement: ", sqlException);
        }
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException sqlException) {
            log.log(Level.SEVERE, "sqlException with connection: ", sqlException);
        }
    }
}
