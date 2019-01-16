package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    public static Connection getConn() {
        Connection conn = null;
        {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&useSSL=false",
                        "root", "coderslab");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }


}
