package com.example.mombaby;

public class RemindersHelper {

    String datetime;

    public  RemindersHelper () {}

    public  RemindersHelper (String datetime) {
        this.datetime = datetime;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return this.datetime;
    }

}
