package com.example.mombaby;

public class ActivityHelper {

       String activity, time, date;

        public ActivityHelper() {

        }
        public ActivityHelper(String activity, String time, String date) {
            this.activity = activity;
            this.time = time;
            this.date = date;
        }


    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
            return this.activity + " " + this.time + " " + this.date;
    }


}
