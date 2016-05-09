package com.hammersmith.fustalfootballbookingfield.model;

/**
 * Created by USER on 12/15/2015.
 */
public class Time {
    int id;
    String time;
    String book;
    public Time(){

    }

    public Time(int id, String time, String book) {
        this.id = id;
        this.time = time;
        this.book = book;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
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
