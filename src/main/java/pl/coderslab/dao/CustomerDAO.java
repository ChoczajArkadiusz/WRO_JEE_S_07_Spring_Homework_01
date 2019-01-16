package pl.coderslab.dao;

import pl.coderslab.beans.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAO {
    private static final String sqlInsert = "INSERT INTO customers(first_name, last_name, pseudonym) VALUES (?, ?, ?)";
    private static final String sqlUpdate = "UPDATE customers SET first_name=?, last_name=?, pseudonym=? WHERE id=?";
    private static final String sqlLoadAll = "SELECT * FROM customers";
    private static final String sqlLoadById = "SELECT * FROM customers WHERE id=?";
    private static final String sqlDelete = "DELETE FROM customers WHERE id=?";


    public static void saveToDb(Customer customer) throws SQLException {
        Connection connection = DBUtil.getConn();
        if (customer.getId() == 0L) {
            String[] generatedColumns = {"id"};
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert, generatedColumns);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getPseudonym());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                customer.setId(rs.getLong(1));
            }
        } else {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getPseudonym());
            preparedStatement.setLong(4, customer.getId());
            preparedStatement.executeUpdate();
        }
    }


    public static void delete(long id) throws SQLException {
        Connection connection = DBUtil.getConn();
        if (id != 0) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }


    public static ArrayList<Customer> loadAll() throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();
        Connection connection = DBUtil.getConn();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlLoadAll);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            do {
                customers.add(extractObject(resultSet));
            }
            while (resultSet.next());
        }
        return customers;
    }


    public static Customer loadById(long id) throws SQLException {
        Connection connection = DBUtil.getConn();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlLoadById);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return extractObject(resultSet);
    }


    private static Customer extractObject(ResultSet resultSet) throws SQLException {
        Customer extractedObject = new Customer();
        extractedObject.setId(resultSet.getLong("id"));
        extractedObject.setFirstName(resultSet.getString("first_name"));
        extractedObject.setLastName(resultSet.getString("last_name"));
        extractedObject.setPseudonym(resultSet.getString("pseudonym"));
        return extractedObject;
    }


}
