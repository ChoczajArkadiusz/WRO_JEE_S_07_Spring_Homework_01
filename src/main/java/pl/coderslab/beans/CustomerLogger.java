package pl.coderslab.beans;

public interface CustomerLogger {

    void log(String message);

    void log(Customer customer, String message);


}
