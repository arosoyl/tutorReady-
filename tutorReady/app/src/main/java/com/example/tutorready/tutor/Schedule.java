package com.example.tutorready.tutor;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Schedule {

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    String day;

    public Schedule(String day, String time) {
        this.day = day;
        this.time = time;
    }

    String time;


}
