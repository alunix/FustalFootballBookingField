package com.hammersmith.fustalfootballbookingfield.model;

/**
 * Created by USER on 11/20/2015.
 */
public class Field {
    private int id;
    private String name;
    private String location;
    private String image;

    public Field(int id, String name, String location, String image) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.image = image;
    }

    public Field() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
