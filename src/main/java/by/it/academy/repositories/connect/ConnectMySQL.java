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
    private final String nameProperties = "/Users/artemaverkov/web_work/src/main/resources/Connection.properties";
    private final Logger logger = Logger.getLogger(ConnectMySQL.class);
        FileInputStream file;
        Properties properties = new Properties();
        {
            try {
                file = new FileInputStream(nameProperties);
                properties.load(file);
            } catch (IOException e) {
                logger.info(e);
            }
        }
    private final String url = properties.getProperty("url") ;
    private final String username = properties.getProperty("username");
    private final String password = properties.getProperty("password");
    private final String DB_DRIVER = properties.getProperty("DB_DRIVER");

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
            logger.info(e);
            return false;
        }
    }
}

