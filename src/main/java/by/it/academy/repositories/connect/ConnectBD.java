package by.it.academy.repositories.connect;

public class ConnectBD {
    private String url;
    private String username;
    private String password;
    private String DB_DRIVER;

    public ConnectBD(String url, String username, String password, String DB_DRIVER) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.DB_DRIVER = DB_DRIVER;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDB_DRIVER() {
        return DB_DRIVER;
    }

    public void setDB_DRIVER(String DB_DRIVER) {
        this.DB_DRIVER = DB_DRIVER;
    }
}
