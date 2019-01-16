package pl.coderslab.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("MemoryCustomerRepository")
public class MemoryCustomerRepository implements CustomerRepository {
    private static List<Customer> customers = new ArrayList();
    CustomerLogger logger;

    @Autowired
    public MemoryCustomerRepository(@Qualifier("DBCustomerLogger") CustomerLogger customerLogger) {
        this.logger = customerLogger;
    }

    @Override
    public void add(Customer customer) {
        customers.add(customer);
        logger.log(customer, "dadano nowego uzytkownika");
    }

    @Override
    public void delete(Customer customer) {
        logger.log(customer, "usunieto uzytkownika");
    }

    @Override
    public List<Customer> getAll() {
        logger.log("Pobrano liste wszystkich uzytownikow");
        return customers;
    }


}
