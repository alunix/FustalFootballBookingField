package com.hammersmith.fustalfootballbookingfield.model;

/**
 * Created by USER on 12/15/2015.
 */
public class Time {
    int id;
    String time;
    public Time(){

    }

    public Time(int id, String time) {
        this.id = id;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
