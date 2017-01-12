package hu.miskolc.uni.iit.dist.integration;

import java.util.Date;

public class SystemMessage {
    private String message;

    private int type;

    private Date date;

    public SystemMessage() {

    }

    public SystemMessage(String message, int type) {
        super();
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        if (this.message == null) {
            return "";
        }

        return this.message + " - " + (this.date == null ? "" : this.date.toString());
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}