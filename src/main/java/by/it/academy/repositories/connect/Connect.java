package by.it.academy.repositories.connect;

import java.sql.Connection;
import java.sql.SQLException;

public interface Connect {
    public Connection connect() throws ClassNotFoundException, SQLException;
    public boolean close();
}


