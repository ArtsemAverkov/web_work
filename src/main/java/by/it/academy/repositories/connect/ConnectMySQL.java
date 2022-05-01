package by.it.academy.repositories.connect;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectMySQL implements Connect {
    private Connection connection;
    private final Logger logger = Logger.getLogger(ConnectMySQL.class);
        FileInputStream file;
        Properties properties = new Properties();
        {
            try {
                file = new FileInputStream("/Users/artemaverkov/web_work/src/main/resources/Connection.properties");
                properties.load(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    private String url = properties.getProperty("url") ;
    String username = properties.getProperty("username");
    String password = properties.getProperty("password");
    String DB_DRIVER = properties.getProperty("DB_DRIVER");

    @Override
    public Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
       connection = DriverManager.getConnection(url, username, password);
        logger.info("Connection to DB successful!");
        return connection;
    }

    @Override
    public boolean close() {
        try {
            connection.close();
            logger.info("Connection to Store DB closed!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

