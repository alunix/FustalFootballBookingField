package com.hammersmith.fustalfootballbookingfield.model;

/**
 * Created by Thuon on 2/26/2016.
 */
public class History {
    private String username;
    private String location;
    private String type;
    private String field;
    private String date;
    private String time;
    private String price;
    private String phone;
    private String email;
    private String address;
    private String no;

    public History() {
    }

    public History(String no, String username, String location, String type, String field, String date, String time, String price, String phone, String email, String address) {
        this.no = no;
        this.username = username;
        this.location = location;
        this.type = type;
        this.field = field;
        this.date = date;
        this.time = time;
        this.price = price;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
