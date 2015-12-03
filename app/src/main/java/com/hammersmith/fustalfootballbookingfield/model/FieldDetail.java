package com.hammersmith.fustalfootballbookingfield.model;

/**
 * Created by USER on 12/2/2015.
 */
public class FieldDetail {
    int id;
    String name;
    String image;
    String weekMrgPri;
    String weekEvePri;
    String weekendMrgPri;
    String getWeekendEvePri;

    public FieldDetail() {
    }

    public FieldDetail(int id, String name, String image, String weekMrgPri, String weekEvePri, String weekendMrgPri, String getWeekendEvePri) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.weekMrgPri = weekMrgPri;
        this.weekEvePri = weekEvePri;
        this.weekendMrgPri = weekendMrgPri;
        this.getWeekendEvePri = getWeekendEvePri;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWeekMrgPri() {
        return weekMrgPri;
    }

    public void setWeekMrgPri(String weekMrgPri) {
        this.weekMrgPri = weekMrgPri;
    }

    public String getWeekEvePri() {
        return weekEvePri;
    }

    public void setWeekEvePri(String weekEvePri) {
        this.weekEvePri = weekEvePri;
    }

    public String getWeekendMrgPri() {
        return weekendMrgPri;
    }

    public void setWeekendMrgPri(String weekendMrgPri) {
        this.weekendMrgPri = weekendMrgPri;
    }

    public String getGetWeekendEvePri() {
        return getWeekendEvePri;
    }

    public void setGetWeekendEvePri(String getWeekendEvePri) {
        this.getWeekendEvePri = getWeekendEvePri;
    }
}
