package pl.coderslab.beans;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class FileCustomerLogger implements CustomerLogger {
    String filename;

    public FileCustomerLogger(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public void log(String message) {
        String logMessage = String.format("%s - %s.\n",
                LocalDateTime.now(), message);
        saveToFile(logMessage);
    }

    @Override
    public void log(Customer customer, String message) {
        String logMessage = String.format("%s - %s - %s %s.\n",
                LocalDateTime.now(), message, customer.getFirstName(), customer.getLastName());
        saveToFile(logMessage);

    }

    private void saveToFile(String logMessage) {
        try {
            FileWriter fileWriter = new FileWriter(filename, true);
            fileWriter.append(logMessage);
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
