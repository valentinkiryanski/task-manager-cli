package dev.lpa;
import java.sql.*;

public class SqlConnect {

    private String url;
    private String user;
    private String password;

    public SqlConnect() {
        this.url = "jdbc:mysql://localhost:3306/task_manager";
        this.user = "root";
        this.password = "password";
    }

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }

    public String getUrl() {
        return url;
    }


}
