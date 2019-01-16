package pl.coderslab.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Qualifier("SimpleCustomerLogger")
public class SimpleCustomerLogger implements CustomerLogger {

    @Override
    public void log(String message) {
        String logMessage = String.format("%s - %s.", LocalDateTime.now(), message);
        System.out.println(logMessage);
    }

    @Override
    public void log(Customer customer, String message) {
        String logMessage = String.format("%s - %s - %s %s.",
                LocalDateTime.now(), message, customer.getFirstName(), customer.getLastName());
        System.out.println(logMessage);
    }


}
