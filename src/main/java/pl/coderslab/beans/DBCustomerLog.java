package pl.coderslab.beans;

import java.sql.Timestamp;

public class DBCustomerLog {
    private Long id;
    private Timestamp dateTime;
    private String message;

    public DBCustomerLog() {
    }

    public DBCustomerLog(Timestamp dateTime, String message) {
        this.dateTime = dateTime;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
