package by.it.academy.repositories.connect;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectMySQL implements Connect {
    private Connection connection;
    private final Logger logger = Logger.getLogger(ConnectMySQL.class);

    private String url = "jdbc:mysql://127.0.0.1:8889/user1";
    private String username = "root";
    private String password = "root";
    private final String DB_DRIVER = "com.mysql.jdbc.Driver";

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

