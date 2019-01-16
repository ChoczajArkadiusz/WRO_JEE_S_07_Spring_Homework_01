package pl.coderslab.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.coderslab.dao.DBCustomerLogDAO;

import java.sql.SQLException;
import java.sql.Timestamp;

@Component
@Qualifier("DBCustomerLogger")
public class DBCustomerLogger implements CustomerLogger {

    @Override
    public void log(String message) {
        save(message);
    }

    @Override
    public void log(Customer customer, String message) {
        String logMessage = String.format("%s - %s %s.",
                message, customer.getFirstName(), customer.getLastName());
        save(logMessage);

    }

    private void save(String logMessage) {
        DBCustomerLog dbCustomerLog = new DBCustomerLog(new Timestamp(System.currentTimeMillis()), logMessage);
        try {
            DBCustomerLogDAO.saveToDb(dbCustomerLog);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
