package pl.coderslab.dao;

import pl.coderslab.beans.DBCustomerLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBCustomerLogDAO {
    private static final String sqlInsert = "INSERT INTO customers_log(datetime, message) VALUES (?, ?)";

    public static void saveToDb(DBCustomerLog log) throws SQLException {
        Connection connection = DBUtil.getConn();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
        preparedStatement.setTimestamp(1, log.getDateTime());
        preparedStatement.setString(2, log.getMessage());
        preparedStatement.executeUpdate();
    }

}
