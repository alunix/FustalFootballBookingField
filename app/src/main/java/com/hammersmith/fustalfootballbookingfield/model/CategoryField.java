package com.hammersmith.fustalfootballbookingfield.model;

/**
 * Created by USER on 11/27/2015.
 */
public class CategoryField {
    private int id;
    private String name;

    public CategoryField(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryField() {
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
}
