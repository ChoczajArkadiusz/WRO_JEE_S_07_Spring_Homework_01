package pl.coderslab.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import pl.coderslab.dao.CustomerDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class DBCustomerRepository implements CustomerRepository {
    CustomerLogger logger;

    @Autowired
    public DBCustomerRepository(@Qualifier("DBCustomerLogger") CustomerLogger customerLogger) {
        this.logger = customerLogger;
    }

    @Override
    public void add(Customer customer) {
        try {
            CustomerDAO.saveToDb(customer);
            logger.log(customer, "dadano nowego uzytkownika");
        } catch (SQLException e) {
            logger.log(customer, "niepowodzenie dadania nowego uzytkownika");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Customer customer) {
        try {
            CustomerDAO.delete(customer.getId());
            logger.log(customer, "usunieto uzytkownika");
        } catch (SQLException e) {
            logger.log(customer, "niepowodzenie usuwania uzytkownika");
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> result = new ArrayList<>();
        try {
            result = CustomerDAO.loadAll();
            logger.log("pobrano wszystkich uzytkownikow z bazy");
        } catch (SQLException e) {
            logger.log("niepowodzenie pobrania wszystkich uzytkownikow z bazy");
            e.printStackTrace();
        }
        return result;
    }


}
