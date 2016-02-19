package com.hammersmith.fustalfootballbookingfield.model;

/**
 * Created by Thuon on 2/12/2016.
 */
public class UserUpdate {
    private String name;
    private String email;
    private String phone;
    private String dob;
    private String address;
    private String gender;

    public UserUpdate(String name, String email, String phone, String dob, String address, String gender) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.address = address;
        this.gender = gender;
    }

    public UserUpdate() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
