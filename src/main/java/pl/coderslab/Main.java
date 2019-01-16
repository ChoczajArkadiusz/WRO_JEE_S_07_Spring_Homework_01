package pl.coderslab;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.coderslab.app.AppConfig;
import pl.coderslab.beans.Customer;
import pl.coderslab.beans.CustomerRepository;
import pl.coderslab.beans.SimpleCustomerLogger;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Zadanie 2
        System.out.println("\nZadanie 2");
        SimpleCustomerLogger simpleCustomerLogger = context.getBean(SimpleCustomerLogger.class);
//        simpleCustomerLogger.log();

        // Zadania 3 - 7
        CustomerRepository customerRepository = context.getBean(CustomerRepository.class);
        Customer customer = context.getBean(Customer.class);
        customer.setFirstName("Jan");
        customer.setLastName("Kowalski");
        customer.setPseudonym("kowal");
        customerRepository.add(customer);

        Customer customer2 = context.getBean(Customer.class);
        customer2.setFirstName("Tomasz");
        customer2.setLastName("Nowak");
        customer2.setPseudonym("tomcio");
        customerRepository.add(customer2);
        customerRepository.delete(customer2);

        context.close();
    }


}
